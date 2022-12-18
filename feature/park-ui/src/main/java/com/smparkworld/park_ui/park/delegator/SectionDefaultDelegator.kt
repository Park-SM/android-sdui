package com.smparkworld.park_ui.park.delegator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.usecase.GetSectionsUseCase
import javax.inject.Inject

class SectionDefaultDelegator @Inject constructor(
    private val getSectionsUseCase: GetSectionsUseCase
) : SectionDelegator {

    override val _sectionDelegatedItems = MutableLiveData<List<SectionDTO>>()

    private val _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> get() = _isLoading

    private val _nextPageTriggerPosition = MutableLiveData<Int>()
    override val nextPageTriggerPosition: LiveData<Int> get() = _nextPageTriggerPosition

    private var nextRequestUrl: String? = null

    override suspend fun requestSections(initRequestUrl: String?) {
        _isLoading.value = true

        if (initRequestUrl != null) {
            when (val result = getSectionsUseCase(initRequestUrl)) {
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

    override suspend fun requestNextSections(origin: List<SectionDTO>) {
        if (_isLoading.value == true) return
        _isLoading.value = true

        val requestUrl = nextRequestUrl
        if (requestUrl != null) {

            when (val result = getSectionsUseCase(requestUrl)) {
                is Result.Success -> {
                    onSuccessGetMoreSections(origin, result.data)
                }
                is Result.Error -> {
                    onFailureGetSections(result.exception)
                }
            }
        }
        _isLoading.value = false
    }

    private fun onSuccessGetSections(data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _nextPageTriggerPosition.value = data.requestUrl?.nextPageTriggerPosition
        _sectionDelegatedItems.value = data.sections
    }

    private fun onSuccessGetMoreSections(origin: List<SectionDTO>, data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _nextPageTriggerPosition.value = data.requestUrl?.nextPageTriggerPosition
        _sectionDelegatedItems.value = origin.toMutableList().also { currentItems ->
            currentItems.addAll(data.sections)
        }
    }

    private fun onFailureGetSections(exception: Exception) {
        // Send non-fatal log, etc..

    }

    private fun onEmptyRequestUrl() {
        // Send non-fatal log, etc..

        _sectionDelegatedItems.value = emptyList()
    }
}