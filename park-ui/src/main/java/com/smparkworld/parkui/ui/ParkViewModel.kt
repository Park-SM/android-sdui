package com.smparkworld.parkui.ui

import android.net.Uri
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smparkworld.core.ExtraKey
import com.smparkworld.core.ui.delegator.WishStatesDelegator
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.parkui.ui.delegator.ParkDelegators
import com.smparkworld.parkui.ui.delegator.RedirectDelegator
import com.smparkworld.parkui.ui.delegator.SectionDelegator
import com.smparkworld.parkui.ui.model.SectionItemEvent
import kotlinx.coroutines.launch

abstract class ParkViewModel(
    private val stateHandle: SavedStateHandle,
    delegators: ParkDelegators
) : ViewModel(),
    ParkEventListener,
    SectionDelegator by delegators.sectionDelegator,
    RedirectDelegator by delegators.redirectDelegator,
    WishStatesDelegator<SectionDTO> by delegators.wishDelegator {

    private val _items: MutableLiveData<List<SectionDTO>> = MediatorLiveData<List<SectionDTO>>().apply {
        addSource(_delegatedItemsBySectionDelegator) { value = it }
        addSource(_delegatedItemsByWishStatesDelegator) { value = it }
    }
    val items: LiveData<List<SectionDTO>> get() = _items

    private val _isEmpty: MutableLiveData<Boolean> get() = MediatorLiveData<Boolean>().apply {
        addSource(_delegatedItemsBySectionDelegator) { value = it.isNullOrEmpty() }
        addSource(_delegatedItemsByWishStatesDelegator) { value = it.isNullOrEmpty() }
    }
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _error: MutableLiveData<Exception> get() = MediatorLiveData<Exception>().apply {
        addSource(_delegatedErrorBySectionDelegator) { value = it }
        addSource(_delegatedErrorByWishStatesDelegator) { value = it }
    }
    val error: LiveData<Exception> get() = _error

    val nextPageTriggerPosition: LiveData<Int?>
        get() = _delegatedNextPageTriggerPositionBySectionDelegator

    val redirectUri: LiveData<Uri>
        get() = _delegatedRedirectUriByRedirectDelegator

    init {
        initSections()
    }

    private fun initSections() {
        viewModelScope.launch {
            requestSections(getRequestUri())
        }
    }

    fun onRequestNextSections() {
        viewModelScope.launch {
            requestNextSections(_items.value ?: emptyList())
        }
    }

    fun onRefreshSections() {
        viewModelScope.launch {
            _items.value?.let { origin ->
                requestPartialUpdateSection(
                    origin = refreshWishItemsByLocalCache(origin)
                )
            }
        }
    }

    override fun onClickSection(v: View, event: SectionItemEvent) {
        when (event) {
            is SectionItemEvent.Click -> {
                redirectToUri(event.linkUri)
            }
            is SectionItemEvent.LongClick -> {
                redirectToUri(event.linkUri)
            }
            is SectionItemEvent.WishClick -> {
                val origin = _items.value ?: return
                val id = event.id
                val isWished = event.isWished

                viewModelScope.launch {
                    requestWishState(origin, id, isWished)
                }
            }
        }
    }

    private fun getRequestUri() = stateHandle.get<String>(ExtraKey.REQUEST_URI)
}