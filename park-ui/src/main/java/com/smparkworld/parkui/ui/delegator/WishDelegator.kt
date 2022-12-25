package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData

internal interface WishDelegator<T> {

    val _wishDelegatedItems: MutableLiveData<List<T>>

    suspend fun requestWishState(origin: List<T>, id: Long, isWished: Boolean)

    suspend fun refreshWishItemsByLocalCache(origin: List<T>)
}