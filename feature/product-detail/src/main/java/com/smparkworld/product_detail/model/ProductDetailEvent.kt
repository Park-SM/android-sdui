package com.smparkworld.product_detail.model

sealed class ProductDetailEvent {

    object InvalidArgument : ProductDetailEvent()
}