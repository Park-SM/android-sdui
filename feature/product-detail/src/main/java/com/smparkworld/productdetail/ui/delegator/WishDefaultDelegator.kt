package com.smparkworld.productdetail.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.domain.Result
import com.smparkworld.domain.usecase.CacheWishUseCase
import com.smparkworld.domain.usecase.CreateWishUseCase
import com.smparkworld.domain.usecase.DeleteWishUseCase
import com.smparkworld.domain.usecase.SyncProductWishStateUseCase
import javax.inject.Inject

class WishDefaultDelegator @Inject constructor(
    private val createWishUseCase: CreateWishUseCase,
    private val deleteWishUseCase: DeleteWishUseCase,
    private val cacheWishUseCase: CacheWishUseCase,
    private val syncProductWishStateUseCase: SyncProductWishStateUseCase
) : WishDelegator {

    override val _isWished = MutableLiveData<Boolean>()

    override suspend fun requestWishState(id: Long, isWished: Boolean) {
        val result = if (isWished) {
            createWishUseCase(id)
        } else {
            deleteWishUseCase(id)
        }
        when (result) {
            is Result.Success -> {
                cacheWishState(id, isWished)
                // do anything on wish api success. e.g) Show Snackbar.. etc..
            }
            is Result.Error -> {
                rollbackWishState(isWished)
                // do anything on wish api failure. e.g) Show Snackbar.. etc..
            }
        }
    }

    override suspend fun refreshWishStateByLocalCache(id: Long) {
        when (val result = syncProductWishStateUseCase(id)) {
            is Result.Success -> {
                result.data?.let { newWishState ->
                    _isWished.value = newWishState
                }
            }
            is Result.Error -> {
                // do nothing
            }
        }
    }

    private suspend fun cacheWishState(id: Long, isWished: Boolean) {
        val payload = CacheWishUseCase.Payload(
            id = id,
            isWished = isWished
        )
        cacheWishUseCase(payload)
    }

    private fun rollbackWishState(isWished: Boolean) {
        _isWished.value = !isWished
    }
}