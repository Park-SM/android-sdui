package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.BuildConfig
import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.usecase.GetSectionsUseCase
import com.smparkworld.domain.usecase.RequestPartialUpdateSectionUseCase
import javax.inject.Inject

class SectionDefaultDelegator @Inject constructor(
    private val getSectionsUseCase: GetSectionsUseCase,
    private val requestPartialUpdateSectionUseCase: RequestPartialUpdateSectionUseCase
) : SectionDelegator {

    override val _delegatedItemsBySectionDelegator = MutableLiveData<List<SectionDTO>>()

    override val _delegatedIsLoadingBySectionDelegator = MutableLiveData<Boolean>()

    override val _delegatedErrorBySectionDelegator = MutableLiveData<Exception>()

    override val _delegatedNextPageTriggerPositionBySectionDelegator = MutableLiveData<Int?>()

    private var nextRequestUri: String? = null

    override suspend fun requestSections(initRequestUri: String?) {
        if (_delegatedIsLoadingBySectionDelegator.value == true) return
        _delegatedIsLoadingBySectionDelegator.value = true

        if (initRequestUri != null) {
            when (val result = getSectionsUseCase(initRequestUri)) {
                is Result.Success -> {
                    onSuccessRequestInternal(result.data)
                    onSuccessRequest(result.data)
                }
                is Result.Error -> {
                    onFailureRequestInternal(result.exception)
                    onFailureRequest(result.exception)
                }
            }
        } else {
            onEmptySectionsInternal()
            onEmptySections()
        }

        _delegatedIsLoadingBySectionDelegator.value = false
    }

    override suspend fun requestNextSections(origin: List<SectionDTO>) {
        if (_delegatedIsLoadingBySectionDelegator.value == true) return
        _delegatedIsLoadingBySectionDelegator.value = true

        val requestUri = nextRequestUri
        if (requestUri != null) {

            when (val result = getSectionsUseCase(requestUri)) {
                is Result.Success -> {
                    onSuccessMoreRequestInternal(origin, result.data)
                    onSuccessMoreRequest(result.data)
                }
                is Result.Error -> {
                    onFailureRequestInternal(result.exception)
                    onFailureRequest(result.exception)
                }
            }
        }
        _delegatedIsLoadingBySectionDelegator.value = false
    }

    override suspend fun requestPartialUpdateSection(origin: List<SectionDTO>) {
        when (val result = requestPartialUpdateSectionUseCase(origin)) {
            is Result.Success -> {
                onSuccessPartialUpdateInternal(result.data)
                onSuccessPartialUpdate(result.data)
            }
            is Result.Error -> {
                onFailureRequestInternal(result.exception)
                onFailureRequest(result.exception)
            }
        }
    }

    private fun onSuccessRequestInternal(data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        _delegatedNextPageTriggerPositionBySectionDelegator.value = data.requestUri?.nextPageTriggerPosition
        _delegatedItemsBySectionDelegator.value = data.sections
    }

    private fun onSuccessMoreRequestInternal(origin: List<SectionDTO>, data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        _delegatedNextPageTriggerPositionBySectionDelegator.value = data.requestUri?.nextPageTriggerPosition
        _delegatedItemsBySectionDelegator.value = origin.toMutableList().also { currentItems ->
            currentItems.addAll(data.sections)
        }
    }

    private fun onSuccessPartialUpdateInternal(updatedSections: List<SectionDTO>) {
        _delegatedItemsBySectionDelegator.value = updatedSections
    }

    private fun onFailureRequestInternal(exception: Exception) {
        // Send non-fatal log, etc..
        if (BuildConfig.DEBUG) exception.printStackTrace()

        _delegatedErrorBySectionDelegator.value = exception
    }

    private fun onEmptySectionsInternal() {
        // Send non-fatal log, etc..

        _delegatedItemsBySectionDelegator.value = emptyList()
    }
}