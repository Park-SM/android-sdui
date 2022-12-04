package com.smparkworld.park.domain.dto

import com.smparkworld.network.model.ParkRequestUrlVO
import com.smparkworld.park.model.Section

data class ParkSectionsDTO(

    var requestUrl: ParkRequestUrlVO? = null,

    var sections: List<Section>? = null,
)
