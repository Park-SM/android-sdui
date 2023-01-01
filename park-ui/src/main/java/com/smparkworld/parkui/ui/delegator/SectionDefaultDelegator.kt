package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.BuildConfig
import com.smparkworld.core.MutableLiveEvent
import com.smparkworld.core.ui.support.recyclerview.BottomLoadState
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

    override val _isLoadingBySectionDelegator = MutableLiveData<Boolean>()

    override val _bottomLoadStateBySectionDelegator = MutableLiveData<BottomLoadState>()

    override val _itemsBySectionDelegator = MutableLiveData<List<SectionDTO>>()

    override val _errorBySectionDelegator = MutableLiveEvent<Exception>()

    override val _nextPageTriggerPositionBySectionDelegator = MutableLiveData<Int?>()

    private var nextRequestUri: String? = null

    override suspend fun requestSections(initRequestUri: String?) {
        if (_isLoadingBySectionDelegator.value == true) return
        _isLoadingBySectionDelegator.value = true

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

        _isLoadingBySectionDelegator.value = false
    }

    override suspend fun requestNextSections(origin: List<SectionDTO>) {
        if (_bottomLoadStateBySectionDelegator.value == BottomLoadState.IsLoading) return
        _bottomLoadStateBySectionDelegator.value = BottomLoadState.IsLoading

        val requestUri = nextRequestUri
        if (requestUri != null) {

            when (val result = getSectionsUseCase(requestUri)) {
                is Result.Success -> {
                    onSuccessMoreRequestInternal(origin, result.data)
                    onSuccessMoreRequest(result.data)

                    _bottomLoadStateBySectionDelegator.value = BottomLoadState.IsNotLoading
                }
                is Result.Error -> {
                    onFailureMoreRequestInternal(result.exception)
                    onFailureMoreRequest(result.exception)

                    _nextPageTriggerPositionBySectionDelegator.value = null
                    _bottomLoadStateBySectionDelegator.value = BottomLoadState.Error(result.exception)
                }
            }
        }
    }

    override suspend fun requestPartialUpdateSection(origin: List<SectionDTO>) {
        when (val result = requestPartialUpdateSectionUseCase(origin)) {
            is Result.Success -> {
                onSuccessPartialUpdateInternal(result.data)
                onSuccessPartialUpdate(result.data)
            }
            is Result.Error -> {
                onFailurePartialUpdateInternal(result.exception)
                onFailurePartialUpdate(result.exception)
            }
        }
    }

    private fun onSuccessRequestInternal(data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        _nextPageTriggerPositionBySectionDelegator.value = data.requestUri?.nextPageTriggerPosition
        _itemsBySectionDelegator.value = data.sections
    }

    private fun onSuccessMoreRequestInternal(origin: List<SectionDTO>, data: ParkSectionsDTO) {
        nextRequestUri = data.requestUri?.nextPageUri

        _nextPageTriggerPositionBySectionDelegator.value = data.requestUri?.nextPageTriggerPosition
        _itemsBySectionDelegator.value = origin.toMutableList().also { currentItems ->
            currentItems.addAll(data.sections)
        }
    }

    private fun onSuccessPartialUpdateInternal(updatedSections: List<SectionDTO>) {
        _itemsBySectionDelegator.value = updatedSections
    }

    private fun onFailureRequestInternal(exception: Exception) {
        // Send non-fatal log, etc..
        if (BuildConfig.DEBUG) exception.printStackTrace()

        _errorBySectionDelegator.value = exception
    }

    private fun onFailurePartialUpdateInternal(exception: Exception) {
        // Send non-fatal log, etc..
        if (BuildConfig.DEBUG) exception.printStackTrace()

        _errorBySectionDelegator.value = exception
    }

    private fun onFailureMoreRequestInternal(exception: Exception) {
        // Send non-fatal log, etc..
        if (BuildConfig.DEBUG) exception.printStackTrace()
    }

    private fun onEmptySectionsInternal() {
        // Send non-fatal log, etc..

        _itemsBySectionDelegator.value = emptyList()
    }
}