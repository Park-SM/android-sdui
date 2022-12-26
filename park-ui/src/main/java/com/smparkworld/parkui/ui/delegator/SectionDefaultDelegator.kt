package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.BuildConfig
import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.usecase.GetSectionsUseCase
import javax.inject.Inject

class SectionDefaultDelegator @Inject constructor(
    private val getSectionsUseCase: GetSectionsUseCase
) : SectionDelegator {

    override val _itemsForDelegatedSection = MutableLiveData<List<SectionDTO>>()

    override val _isLoadingForDelegatedSection = MutableLiveData<Boolean>()

    override val _errorForDelegatedSection = MutableLiveData<Exception>()

    override val _nextPageTriggerPosition = MutableLiveData<Int?>()

    private var nextRequestUri: String? = null

    override suspend fun requestSections(initRequestUri: String?) {
        _isLoadingForDelegatedSection.value = true

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

        _isLoadingForDelegatedSection.value = false
    }

    override suspend fun requestNextSections(origin: List<SectionDTO>) {
        if (_isLoadingForDelegatedSection.value == true) return
        _isLoadingForDelegatedSection.value = true

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
        _isLoadingForDelegatedSection.value = false
    }

    override suspend fun requestPartialSectionUpdate(origin: List<SectionDTO>) {
        TODO("Not yet implemented")
    }

    private fun onSuccessRequestInternal(data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        _nextPageTriggerPosition.value = data.requestUri?.nextPageTriggerPosition
        _itemsForDelegatedSection.value = data.sections
    }

    private fun onSuccessMoreRequestInternal(origin: List<SectionDTO>, data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        _nextPageTriggerPosition.value = data.requestUri?.nextPageTriggerPosition
        _itemsForDelegatedSection.value = origin.toMutableList().also { currentItems ->
            currentItems.addAll(data.sections)
        }
    }

    private fun onFailureRequestInternal(exception: Exception) {
        // Send non-fatal log, etc..
        if (BuildConfig.DEBUG) exception.printStackTrace()

        _errorForDelegatedSection.value = exception
    }

    private fun onEmptySectionsInternal() {
        // Send non-fatal log, etc..

        _itemsForDelegatedSection.value = emptyList()
    }
}