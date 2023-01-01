package com.smparkworld.core.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.MutableLiveEvent

interface WishStatesDelegator<T> {

    val _itemsByWishStatesDelegator: MutableLiveData<List<T>>

    val _errorByWishStatesDelegator: MutableLiveEvent<Exception>

    suspend fun requestWishState(origin: List<T>, id: Long, isWished: Boolean)

    suspend fun refreshWishItemsByLocalCache(origin: List<T>): List<T>

    fun onRequestWishState(origin: List<T>, id: Long, isWished: Boolean) {}
}