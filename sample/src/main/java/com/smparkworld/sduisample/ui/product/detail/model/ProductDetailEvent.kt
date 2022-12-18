package com.smparkworld.sduisample.ui.product.detail.model

sealed class ProductDetailEvent {

    object InvalidArgument : ProductDetailEvent()
}