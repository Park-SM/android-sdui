package com.smparkworld.park_ui.park.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.usecase.CacheWishUseCase
import com.smparkworld.domain.usecase.CreateWishUseCase
import com.smparkworld.domain.usecase.DeleteWishUseCase
import com.smparkworld.domain.usecase.RollbackSectionWishStateUseCase
import com.smparkworld.domain.usecase.SyncSectionWishStateUseCase
import javax.inject.Inject

class SectionWishDelegator @Inject constructor(
    private val createWishUseCase: CreateWishUseCase,
    private val deleteWishUseCase: DeleteWishUseCase,
    private val rollbackSectionWishStateUseCase: RollbackSectionWishStateUseCase,
    private val cacheWishUseCase: CacheWishUseCase,
    private val syncSectionWishStateUseCase: SyncSectionWishStateUseCase
) : WishDelegator<SectionDTO> {

    override val _wishDelegatedItems = MutableLiveData<List<SectionDTO>>()

    override suspend fun requestWishState(origin: List<SectionDTO>, id: Long, isWished: Boolean) {
        val result = if (isWished) {
            createWishUseCase(id)
        } else {
            deleteWishUseCase(id)
        }
        when (result) {
            is Result.Success -> {
                cacheWishState(id, isWished)

                // Requested a item can be multiple items in the list, apply wish state changes.
                refreshWishItemsByLocalCache(origin)

                // do anything on wish api success. e.g) Show Snackbar.. etc..
            }
            is Result.Error -> {
                rollbackWishState(origin, id, isWished)
                // do anything on wish api failure. e.g) Show Snackbar.. etc..
            }
        }
    }

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

    private suspend fun cacheWishState(id: Long, isWished: Boolean) {
        val payload = CacheWishUseCase.Payload(
            id = id,
            isWished = isWished
        )
        cacheWishUseCase(payload)
    }

    private suspend fun rollbackWishState(origin: List<SectionDTO>, id: Long, isWished: Boolean) {

        val payload = RollbackSectionWishStateUseCase.Payload(
            id = id,
            isWished = isWished,
            originItems = origin
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