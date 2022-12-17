package com.smparkworld.park.ui.park

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import com.smparkworld.core.ExtraKey
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.usecase.GetSectionsUseCase
import com.smparkworld.park.model.Result
import com.smparkworld.park.ui.base.BaseViewModel
import com.smparkworld.park.ui.park.delegator.RedirectDefaultDelegator
import com.smparkworld.park.ui.park.delegator.RedirectDelegator
import com.smparkworld.park.ui.park.delegator.SectionWishDelegator
import com.smparkworld.park.ui.park.delegator.WishDelegator
import com.smparkworld.park.ui.park.model.SectionItemEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ParkViewModel @Inject constructor(
    private val getSectionsUseCase: GetSectionsUseCase,
    private val stateHandle: SavedStateHandle,
    redirectDefaultDelegator: RedirectDefaultDelegator,
    sectionWishDelegator: SectionWishDelegator
) : BaseViewModel(),
    ParkEventListener,
    RedirectDelegator by redirectDefaultDelegator,
    WishDelegator<SectionDTO> by sectionWishDelegator {

    private val _sectionItems = MutableLiveData<List<SectionDTO>>()

    private val _items: MutableLiveData<List<SectionDTO>> = MediatorLiveData<List<SectionDTO>>().apply {
        addSource(_sectionItems) { value = it }
        addSource(_wishDelegatedItems) {
            value = it
        }
    }
    val items: LiveData<List<SectionDTO>> get() = _items

    val isEmpty: LiveData<Boolean> get() = Transformations.map(_items) {
        it.isNullOrEmpty()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _nextPageTriggerPosition = MutableLiveData<Int>()
    val nextPageTriggerPosition: LiveData<Int> get() = _nextPageTriggerPosition

    private var nextRequestUrl: String? = null

    init {
        requestSections()
    }

    private fun requestSections() {
        _isLoading.value = true

        viewModelScope.launch {
            val requestUrl = getRequestUrl()
            if (requestUrl != null) {

                when (val result = getSectionsUseCase(requestUrl)) {
                    is Result.Success -> {
                        onSuccessGetSections(result.data)
                    }
                    is Result.Error -> {
                        onFailureGetSections(result.exception)
                    }
                }
            } else {
                onEmptyRequestUrl()
            }

            _isLoading.value = false
        }
    }

    fun requestNextSections() {
        if (_isLoading.value == true) return
        _isLoading.value = true

        viewModelScope.launch {
            val requestUrl = nextRequestUrl
            if (requestUrl != null) {

                when (val result = getSectionsUseCase(requestUrl)) {
                    is Result.Success -> {
                        onSuccessGetMoreSections(result.data)
                    }
                    is Result.Error -> {
                        onFailureGetSections(result.exception)
                    }
                }
            }
            _isLoading.value = false
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

    fun refreshItems() {
        viewModelScope.launch {
            _items.value?.let { origin ->
                refreshWishItemsByLocalCache(origin)
            }
            // add partial update logics
        }
    }

    private fun onSuccessGetSections(data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _nextPageTriggerPosition.value = data.requestUrl?.nextPageTriggerPosition
        _sectionItems.value = data.sections
    }

    private fun onSuccessGetMoreSections(data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _nextPageTriggerPosition.value = data.requestUrl?.nextPageTriggerPosition
        _sectionItems.value = (_items.value ?: emptyList()).toMutableList().also { currentItems ->
            currentItems.addAll(data.sections)
        }
    }

    private fun onFailureGetSections(exception: Exception) {
        // Send non-fatal log, etc..

    }

    private fun onEmptyRequestUrl() {
        // Send non-fatal log, etc..

        _sectionItems.value = emptyList()
    }

    private fun getRequestUrl() = stateHandle.get<String>(ExtraKey.REQUEST_URL)
}