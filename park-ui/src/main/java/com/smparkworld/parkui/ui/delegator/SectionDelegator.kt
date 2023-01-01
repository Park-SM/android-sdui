package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.MutableLiveEvent
import com.smparkworld.core.ui.support.recyclerview.BottomLoadState
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO

interface SectionDelegator {

    val _isLoadingBySectionDelegator: MutableLiveData<Boolean>

    val _bottomLoadStateBySectionDelegator: MutableLiveData<BottomLoadState>

    val _itemsBySectionDelegator: MutableLiveData<List<SectionDTO>>

    val _errorBySectionDelegator: MutableLiveEvent<Exception>

    val _nextPageTriggerPositionBySectionDelegator: MutableLiveData<Int?>

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