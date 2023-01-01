package com.smparkworld.core.ui.delegator

import androidx.lifecycle.MutableLiveData

interface WishStateDelegator {

    val _delegatedIsWishedByWishStateDelegator: MutableLiveData<Boolean>

    val _delegatedErrorByWishStateDelegator: MutableLiveData<Exception>

    suspend fun requestWishState(id: Long, isWished: Boolean)

    suspend fun refreshWishStateByLocalCache(id: Long)

    fun onRequestWishState(id: Long, isWished: Boolean) {}
}