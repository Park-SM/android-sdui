package com.smparkworld.core.ui.delegator

import androidx.lifecycle.MutableLiveData

interface WishStateDelegator {

    val _isWished: MutableLiveData<Boolean>

    suspend fun requestWishState(id: Long, isWished: Boolean)

    suspend fun refreshWishStateByLocalCache(id: Long)
}