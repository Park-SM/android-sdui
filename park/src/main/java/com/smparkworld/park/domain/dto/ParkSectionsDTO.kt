package com.smparkworld.park.domain.dto

data class ParkSectionsDTO(

    var requestUrl: ParkRequestUrlDTO? = null,

    var sections: List<SectionDTO>
)