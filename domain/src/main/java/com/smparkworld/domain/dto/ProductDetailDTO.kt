package com.smparkworld.domain.dto

data class ProductDetailDTO(

    var id: Long? = null,

    var imageUri: String? = null,

    var title: String? = null,

    var category: String? = null,

    var reviewScore: String? = null,

    var price: String? = null,

    var isWished: Boolean? = null,
)
