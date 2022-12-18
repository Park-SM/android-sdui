package com.smparkworld.park.ui.park.delegator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smparkworld.park.domain.dto.SectionDTO

interface SectionDelegator {

    val _sectionDelegatedItems: MutableLiveData<List<SectionDTO>>

    val nextPageTriggerPosition: LiveData<Int>

    val isLoading: LiveData<Boolean>

    suspend fun requestSections(initRequestUrl: String?)
    suspend fun requestNextSections(origin: List<SectionDTO>)
}