package com.smparkworld.domain.repository

import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO

interface SectionRepository {

    suspend fun requestSections(uri: String): ParkSectionsDTO

    suspend fun cloneSection(sectionDTO: SectionDTO): SectionDTO

    suspend fun requestPartialUpdateSections(sectionDTOs: List<SectionDTO>): List<SectionDTO>
}