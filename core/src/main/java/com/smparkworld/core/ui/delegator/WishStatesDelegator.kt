package com.smparkworld.core.ui.delegator

import androidx.lifecycle.MutableLiveData

interface WishStatesDelegator<T> {

    val _wishDelegatedItems: MutableLiveData<List<T>>

    suspend fun requestWishState(origin: List<T>, id: Long, isWished: Boolean)

    suspend fun refreshWishItemsByLocalCache(origin: List<T>)
}