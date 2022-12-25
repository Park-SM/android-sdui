package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO

interface SectionDelegator {

    val _itemsForDelegatedSection: MutableLiveData<List<SectionDTO>>

    val _isLoadingForDelegatedSection: MutableLiveData<Boolean>

    val _errorForDelegatedSection: MutableLiveData<Exception>

    val _nextPageTriggerPosition: MutableLiveData<Int?>

    suspend fun requestSections(initRequestUri: String?)
    suspend fun requestNextSections(origin: List<SectionDTO>)

    fun onSuccessRequest(data: ParkSectionsDTO) {}
    fun onSuccessMoreRequest(data: ParkSectionsDTO) {}
    fun onFailureRequest(exception: Exception) {}
    fun onEmptySections() {}
}