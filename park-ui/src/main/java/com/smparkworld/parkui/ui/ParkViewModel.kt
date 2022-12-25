package com.smparkworld.parkui.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.smparkworld.core.ExtraKey
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.parkui.ui.delegator.RedirectDefaultDelegator
import com.smparkworld.parkui.ui.delegator.RedirectDelegator
import com.smparkworld.parkui.ui.delegator.SectionDefaultDelegator
import com.smparkworld.parkui.ui.delegator.SectionDelegator
import com.smparkworld.parkui.ui.delegator.SectionWishStatesDelegator
import com.smparkworld.core.ui.delegator.WishStatesDelegator
import com.smparkworld.parkui.ui.model.SectionItemEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ParkViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    sectionDefaultDelegator: SectionDefaultDelegator,
    redirectDefaultDelegator: RedirectDefaultDelegator,
    sectionWishDelegator: SectionWishStatesDelegator
) : ViewModel(),
    ParkEventListener,
    SectionDelegator by sectionDefaultDelegator,
    RedirectDelegator by redirectDefaultDelegator,
    WishStatesDelegator<SectionDTO> by sectionWishDelegator {

    private val _items: MutableLiveData<List<SectionDTO>> = MediatorLiveData<List<SectionDTO>>().apply {
        addSource(_sectionDelegatedItems) { value = it }
        addSource(_wishDelegatedItems) { value = it }
    }
    val items: LiveData<List<SectionDTO>> get() = _items
    val isEmpty: LiveData<Boolean> get() = _items.map { it.isNullOrEmpty() }

    init {
        initialize()
    }

    fun onRequestNextSections() {
        viewModelScope.launch {
            requestNextSections(_items.value ?: emptyList())
        }
    }

    fun onRefreshItems() {
        viewModelScope.launch {
            _items.value?.let { origin ->
                refreshWishItemsByLocalCache(origin)
                // add partial update logics
            }
        }
    }

    override fun onClickItem(v: View, event: SectionItemEvent) {
        when (event) {
            is SectionItemEvent.Click -> {
                val linkUri = event.model.getRedirectUri() ?: return

                redirectToUri(linkUri)
            }
            is SectionItemEvent.LongClick -> {
                val linkUri = event.model.getRedirectUri() ?: return

                redirectToUri(linkUri)
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
        requestSections(getRequestUri())
    }

    private fun getRequestUri() = stateHandle.get<String>(ExtraKey.REQUEST_URI)
}