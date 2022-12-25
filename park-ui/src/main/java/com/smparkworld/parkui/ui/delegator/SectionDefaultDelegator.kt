package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.LiveData
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

    override val _sectionDelegatedItems = MutableLiveData<List<SectionDTO>>()

    private val _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> get() = _isLoading

    private val _nextPageTriggerPosition = MutableLiveData<Int>()
    override val nextPageTriggerPosition: LiveData<Int> get() = _nextPageTriggerPosition

    private var nextRequestUri: String? = null

    override suspend fun requestSections(initRequestUri: String?) {
        _isLoading.value = true

        if (initRequestUri != null) {
            when (val result = getSectionsUseCase(initRequestUri)) {
                is Result.Success -> {
                    onSuccessGetSections(result.data)
                }
                is Result.Error -> {
                    onFailureGetSections(result.exception)
                }
            }
        } else {
            onEmptyRequestUri()
        }

        _isLoading.value = false
    }

    override suspend fun requestNextSections(origin: List<SectionDTO>) {
        if (_isLoading.value == true) return
        _isLoading.value = true

        val requestUri = nextRequestUri
        if (requestUri != null) {

            when (val result = getSectionsUseCase(requestUri)) {
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
        nextRequestUri = data.requestUri?.nextPageUri

        data.requestUri?.nextPageTriggerPosition?.let { position ->
            _nextPageTriggerPosition.value = position
        }
        _sectionDelegatedItems.value = data.sections
    }

    private fun onSuccessGetMoreSections(origin: List<SectionDTO>, data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        data.requestUri?.nextPageTriggerPosition?.let { position ->
            _nextPageTriggerPosition.value = position
        }
        _sectionDelegatedItems.value = origin.toMutableList().also { currentItems ->
            currentItems.addAll(data.sections)
        }
    }

    private fun onFailureGetSections(exception: Exception) {
        // Send non-fatal log, etc..
        if (BuildConfig.DEBUG) exception.printStackTrace()
    }

    private fun onEmptyRequestUri() {
        // Send non-fatal log, etc..

        _sectionDelegatedItems.value = emptyList()
    }
}