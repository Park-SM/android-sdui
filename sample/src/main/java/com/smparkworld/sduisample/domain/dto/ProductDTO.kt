package com.smparkworld.sduisample.domain.dto

data class ProductDTO(

    var id: Long? = null,

    var imageUrl: String? = null,

    var title: String? = null,

    var category: String? = null,

    var reviewScore: String? = null,

    var price: String? = null,

    var isWished: Boolean? = null,
)