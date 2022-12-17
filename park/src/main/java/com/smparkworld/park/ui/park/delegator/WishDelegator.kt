package com.smparkworld.park.ui.park.delegator

import androidx.lifecycle.MutableLiveData

interface WishDelegator<T> {

    val _wishDelegatedItems: MutableLiveData<List<T>>

    suspend fun refreshWishItemsByLocalCache(origin: List<T>)

    suspend fun requestWishState(origin: List<T>, id: Long, isWished: Boolean)
}