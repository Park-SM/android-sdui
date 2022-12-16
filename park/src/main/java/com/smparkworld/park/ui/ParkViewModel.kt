package com.smparkworld.park.ui

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
import com.smparkworld.park.ui.delegator.RedirectDefaultDelegator
import com.smparkworld.park.ui.delegator.RedirectDelegator
import com.smparkworld.park.ui.delegator.SectionWishDelegator
import com.smparkworld.park.ui.delegator.WishDelegator
import com.smparkworld.park.ui.model.SectionItemEvent
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

    private val _items = MutableLiveData<List<SectionDTO>>()

    val items: LiveData<List<SectionDTO>> get() = MediatorLiveData<List<SectionDTO>>().apply {
        addSource(_items) { value = it }
        addSource(_wishDelegatedItems) { value = it }
    }

    val isEmpty: LiveData<Boolean> get() = Transformations.map(_items) {
        it.isNullOrEmpty()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var nextRequestUrl: String? = null

    init {
        requestSections()
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
                val id = event.model.getWishTargetId() ?: return
                val isWished = event.isWished

                viewModelScope.launch {
                    requestWishState(id, isWished)
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

    private fun requestSections() {

        viewModelScope.launch {
            _isLoading.value = true

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



    private fun onSuccessGetSections(data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _items.value = data.sections
    }

    private fun onFailureGetSections(exception: Exception) {
        // Send non-fatal log, etc..

    }

    private fun onEmptyRequestUrl() {
        // Send non-fatal log, etc..

        _items.value = emptyList()
    }

    private fun getRequestUrl() = stateHandle.get<String>(ExtraKey.REQUEST_URL)
}