package com.smparkworld.park.domain.repository

import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.dto.SectionDTO

interface SectionRepository {

    suspend fun requestSections(url: String): ParkSectionsDTO

    suspend fun cloneSection(section: SectionDTO): SectionDTO
}