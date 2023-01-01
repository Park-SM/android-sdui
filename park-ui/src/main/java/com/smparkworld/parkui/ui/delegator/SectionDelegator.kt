package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.ui.delegator.BottomLoadStateDelegator.BottomLoadState
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO

interface SectionDelegator {

    val _delegatedIsLoadingBySectionDelegator: MutableLiveData<Boolean>

    val _delegatedBottomLoadStateBySectionDelegator: MutableLiveData<BottomLoadState>

    val _delegatedItemsBySectionDelegator: MutableLiveData<List<SectionDTO>>

    val _delegatedErrorBySectionDelegator: MutableLiveData<Exception>

    val _delegatedNextPageTriggerPositionBySectionDelegator: MutableLiveData<Int?>

    suspend fun requestSections(initRequestUri: String?)
    suspend fun requestNextSections(origin: List<SectionDTO>)
    suspend fun requestPartialUpdateSection(origin: List<SectionDTO>)

    fun onSuccessRequest(data: ParkSectionsDTO) {}
    fun onSuccessMoreRequest(data: ParkSectionsDTO) {}
    fun onSuccessPartialUpdate(items: List<SectionDTO>) {}
    fun onFailureRequest(exception: Exception) {}
    fun onFailureMoreRequest(exception: Exception) {}
    fun onFailurePartialUpdate(exception: Exception) {}
    fun onEmptySections() {}
}