package com.smparkworld.park.domain.repository

import com.smparkworld.park.domain.dto.ParkSectionsDTO

interface ParkRepository {

    suspend fun requestSections(url: String): ParkSectionsDTO
}