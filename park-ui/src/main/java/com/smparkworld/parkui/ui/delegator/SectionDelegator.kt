package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smparkworld.domain.dto.SectionDTO

interface SectionDelegator {

    val _sectionDelegatedItems: MutableLiveData<List<SectionDTO>>

    val nextPageTriggerPosition: LiveData<Int>

    val isLoading: LiveData<Boolean>

    suspend fun requestSections(initRequestUri: String?)
    suspend fun requestNextSections(origin: List<SectionDTO>)
}