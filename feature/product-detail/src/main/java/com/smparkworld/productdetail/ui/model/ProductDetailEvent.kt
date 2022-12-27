package com.smparkworld.productdetail.ui.model

sealed class ProductDetailEvent {

    object InvalidArgument : ProductDetailEvent()
}