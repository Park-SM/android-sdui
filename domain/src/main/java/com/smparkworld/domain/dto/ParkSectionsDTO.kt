package com.smparkworld.domain.dto

data class ParkSectionsDTO(

    var requestUri: ParkRequestUriDTO? = null,

    var sections: List<SectionDTO>
)