package com.smparkworld.core.ui.delegator

import androidx.lifecycle.MutableLiveData

interface WishStatesDelegator<T> {

    val _delegatedItemsByWishStatesDelegator: MutableLiveData<List<T>>

    val _delegatedErrorByWishStatesDelegator: MutableLiveData<Exception>

    suspend fun requestWishState(origin: List<T>, id: Long, isWished: Boolean)

    suspend fun refreshWishItemsByLocalCache(origin: List<T>): List<T>

    fun onRequestWishState(origin: List<T>, id: Long, isWished: Boolean) {}
}