package com.smparkworld.park_ui.park

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import com.smparkworld.core.ExtraKey
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.park_ui.park.delegator.RedirectDefaultDelegator
import com.smparkworld.park_ui.park.delegator.RedirectDelegator
import com.smparkworld.park_ui.park.delegator.SectionDefaultDelegator
import com.smparkworld.park_ui.park.delegator.SectionDelegator
import com.smparkworld.park_ui.park.delegator.SectionWishDelegator
import com.smparkworld.park_ui.park.delegator.WishDelegator
import com.smparkworld.park_ui.park.model.SectionItemEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ParkViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    sectionDefaultDelegator: SectionDefaultDelegator,
    redirectDefaultDelegator: RedirectDefaultDelegator,
    sectionWishDelegator: SectionWishDelegator
) : com.smparkworld.park_ui.base.BaseViewModel(),
    ParkEventListener,
    SectionDelegator by sectionDefaultDelegator,
    RedirectDelegator by redirectDefaultDelegator,
    WishDelegator<SectionDTO> by sectionWishDelegator {

    private val _items: MutableLiveData<List<SectionDTO>> = MediatorLiveData<List<SectionDTO>>().apply {
        addSource(_sectionDelegatedItems) { value = it }
        addSource(_wishDelegatedItems) { value = it }
    }
    val items: LiveData<List<SectionDTO>> get() = _items
    val isEmpty: LiveData<Boolean> get() = Transformations.map(_items) { it.isNullOrEmpty() }

    init {
        initialize()
    }

    fun requestNextSections() {
        viewModelScope.launch {
            requestNextSections(_items.value ?: emptyList())
        }
    }

    fun refreshItems() {
        viewModelScope.launch {
            _items.value?.let { origin ->
                refreshWishItemsByLocalCache(origin)
            }
            // add partial update logics
        }
    }

    override fun onClickItem(v: View, event: SectionItemEvent) {
        when (event) {
            is SectionItemEvent.Click -> {
                val linkUrl = event.model.getRedirectUrl() ?: return

                redirectToUrl(linkUrl)
            }
            is SectionItemEvent.LongClick -> {
                val linkUrl = event.model.getRedirectUrl() ?: return

                redirectToUrl(linkUrl)
            }
            is SectionItemEvent.WishClick -> {
                val origin = _items.value ?: return
                val id = event.model.getWishTargetId() ?: return
                val isWished = event.isWished

                viewModelScope.launch {
                    requestWishState(origin, id, isWished)
                }
            }
        }
    }

    private fun initialize() = viewModelScope.launch {
        requestSections(getRequestUrl())
    }

    private fun getRequestUrl() = stateHandle.get<String>(ExtraKey.REQUEST_URL)
}