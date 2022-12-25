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
        addSource(_itemsForDelegatedSection) { value = it }
        addSource(_itemsForDelegatedWish) { value = it }
    }
    val items: LiveData<List<SectionDTO>> get() = _items

    private val _isEmpty: MutableLiveData<Boolean> get() = MediatorLiveData<Boolean>().apply {
        addSource(_itemsForDelegatedSection) { value = it.isNullOrEmpty() }
        addSource(_itemsForDelegatedWish) { value = it.isNullOrEmpty() }
    }
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _error: MutableLiveData<Exception> get() = MediatorLiveData<Exception>().apply {
        addSource(_errorForDelegatedSection) { value = it}
        addSource(_errorForDelegatedWish) { value = it }
    }
    val error: LiveData<Exception> get() = _error

    val nextPageTriggerPosition: LiveData<Int?> get() = _nextPageTriggerPosition

    val redirectUri: LiveData<Uri> get() = _redirectUri

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
                refreshWishItemsByLocalCache(origin)
                // add partial update logics
            }
        }
    }

    override fun onClickSection(v: View, event: SectionItemEvent) {
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

    private fun getRequestUri() = stateHandle.get<String>(ExtraKey.REQUEST_URI)
}