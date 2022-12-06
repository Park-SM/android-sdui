package com.smparkworld.park.model.sections

import com.smparkworld.park.domain.dto.SectionDTO

data class ProductSectionDTO(

    val id: Long? = null,

    val imageUrl: String? = null,

    val title: String? = null,

    val category: String? = null,

    val reviewScore: String? = null,

    val price: String? = null

) : SectionDTO()