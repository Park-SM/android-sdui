package com.smparkworld.park.domain.dto

import com.smparkworld.network.model.ParkRequestUrlVO
import com.smparkworld.park.model.Section

data class ParkSectionsDTO(

    val requestUrl: ParkRequestUrlVO? = null,

    val sections: List<Section>? = null,
)
