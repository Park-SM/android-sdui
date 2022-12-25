package com.smparkworld.productdetail.ui.delegator

import androidx.lifecycle.MutableLiveData

interface WishDelegator {

    val _isWished: MutableLiveData<Boolean>

    suspend fun requestWishState(id: Long, isWished: Boolean)

    suspend fun refreshWishStateByLocalCache(id: Long)
}