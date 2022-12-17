package com.smparkworld.park.ui.park.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.usecase.CacheWishUseCase
import com.smparkworld.park.domain.usecase.CreateWishUseCase
import com.smparkworld.park.domain.usecase.DeleteWishUseCase
import com.smparkworld.park.domain.usecase.RollbackSectionWishStateUseCase
import com.smparkworld.park.domain.usecase.SyncSectionWishStateUseCase
import com.smparkworld.park.model.Result
import javax.inject.Inject

class SectionWishDelegator @Inject constructor(
    private val createWishUseCase: CreateWishUseCase,
    private val deleteWishUseCase: DeleteWishUseCase,
    private val rollbackSectionWishStateUseCase: RollbackSectionWishStateUseCase,
    private val cacheWishUseCase: CacheWishUseCase,
    private val syncSectionWishStateUseCase: SyncSectionWishStateUseCase
) : WishDelegator<SectionDTO> {

    override val _wishDelegatedItems = MutableLiveData<List<SectionDTO>>()

    override suspend fun refreshWishItemsByLocalCache(origin: List<SectionDTO>) {
        when (val result = syncSectionWishStateUseCase(origin)) {
            is Result.Success -> {
                _wishDelegatedItems.value = result.data
            }
            is Result.Error -> {
                // do nothing
            }
        }
    }

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
                onRollbackWishState(id, isWished)
                // do anything on wish api failure. e.g) Show Snackbar.. etc..
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

    private suspend fun onRollbackWishState(id: Long, isWished: Boolean) {

        val payload = RollbackSectionWishStateUseCase.Payload(
            id = id,
            isWished = isWished,
            originItems = _wishDelegatedItems.value ?: return
        )

        when (val result = rollbackSectionWishStateUseCase(payload)) {
            is Result.Success -> {
                _wishDelegatedItems.value = result.data
            }
            is Result.Error -> {
                // Send non-fatal log, etc..
            }
        }
    }
}