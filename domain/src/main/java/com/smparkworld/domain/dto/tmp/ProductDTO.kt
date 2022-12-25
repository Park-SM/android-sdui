package com.smparkworld.domain.dto.tmp

data class ProductDTO(

    var id: Long? = null,

    var imageUri: String? = null,

    var title: String? = null,

    var category: String? = null,

    var reviewScore: String? = null,

    var price: String? = null,

    var isWished: Boolean? = null,
)
