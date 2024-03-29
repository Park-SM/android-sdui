package com.smparkworld.productdetail.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.ui.delegator.WishStateDelegator
import com.smparkworld.domain.Result
import com.smparkworld.domain.usecase.CacheWishUseCase
import com.smparkworld.domain.usecase.CreateWishUseCase
import com.smparkworld.domain.usecase.DeleteWishUseCase
import com.smparkworld.domain.usecase.SyncProductWishStateUseCase
import javax.inject.Inject

internal class ProductWishStateDelegator @Inject constructor(
    private val createWishUseCase: CreateWishUseCase,
    private val deleteWishUseCase: DeleteWishUseCase,
    private val cacheWishUseCase: CacheWishUseCase,
    private val syncProductWishStateUseCase: SyncProductWishStateUseCase
) : WishStateDelegator {

    override val _delegatedIsWishedByWishStateDelegator = MutableLiveData<Boolean>()

    override val _delegatedErrorByWishStateDelegator = MutableLiveData<Exception>()

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

                _delegatedErrorByWishStateDelegator.value = result.exception

                // do anything on wish api failure. e.g) Show Snackbar.. etc..
            }
        }
        onRequestWishState(id, isWished)
    }

    override suspend fun refreshWishStateByLocalCache(id: Long) {
        when (val result = syncProductWishStateUseCase(id)) {
            is Result.Success -> {
                result.data?.let { newWishState ->
                    _delegatedIsWishedByWishStateDelegator.value = newWishState
                }
            }
            is Result.Error -> {
                _delegatedErrorByWishStateDelegator.value = result.exception
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
        _delegatedIsWishedByWishStateDelegator.value = !isWished
    }
}