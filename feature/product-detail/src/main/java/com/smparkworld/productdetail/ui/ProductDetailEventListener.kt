package com.smparkworld.productdetail.ui

import android.view.View
import androidx.lifecycle.LiveData
import com.smparkworld.productdetail.ui.model.ProductDetailEvent

interface ProductDetailEventListener {

    val imageUri: LiveData<String>

    val isWished: LiveData<Boolean>

    val title: LiveData<String>

    val reviewScore: LiveData<String>

    val price: LiveData<String>

    val category: LiveData<String>

    val event: LiveData<ProductDetailEvent>

    fun onClickWish(v: View)
}