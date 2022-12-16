package com.smparkworld.park.ui.delegator

import androidx.lifecycle.MutableLiveData

interface WishDelegator<T> {

    val _wishDelegatedItems: MutableLiveData<List<T>>

    suspend fun refreshWishItemsByLocalCache(origin: List<T>)

    suspend fun requestWishState(id: Long, isWished: Boolean)
}