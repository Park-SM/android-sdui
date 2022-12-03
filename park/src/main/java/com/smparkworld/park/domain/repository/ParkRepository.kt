package com.smparkworld.park.domain.repository

import com.smparkworld.park.model.Section

interface ParkRepository {

    suspend fun requestSections(url: String): List<Section>
}