package com.smparkworld.parkui.ui.delegator

import androidx.lifecycle.MutableLiveData
import com.smparkworld.core.ui.delegator.WishStatesDelegator
import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.usecase.CacheWishUseCase
import com.smparkworld.domain.usecase.CreateWishUseCase
import com.smparkworld.domain.usecase.DeleteWishUseCase
import com.smparkworld.domain.usecase.RollbackSectionWishStateUseCase
import com.smparkworld.domain.usecase.SyncSectionsWishStateUseCase
import javax.inject.Inject

class SectionWishStatesDelegator @Inject constructor(
    private val createWishUseCase: CreateWishUseCase,
    private val deleteWishUseCase: DeleteWishUseCase,
    private val rollbackSectionWishStateUseCase: RollbackSectionWishStateUseCase,
    private val cacheWishUseCase: CacheWishUseCase,
    private val syncSectionsWishStateUseCase: SyncSectionsWishStateUseCase
) : WishStatesDelegator<SectionDTO> {

    override val _itemsForDelegatedWish = MutableLiveData<List<SectionDTO>>()

    override val _errorForDelegatedWish = MutableLiveData<Exception>()

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
        when (val result = syncSectionsWishStateUseCase(origin)) {
            is Result.Success -> {
                _itemsForDelegatedWish.value = result.data
            }
            is Result.Error -> {
                _errorForDelegatedWish.value = result.exception
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
                _itemsForDelegatedWish.value = result.data
            }
            is Result.Error -> {
                _errorForDelegatedWish.value = result.exception
            }
        }
    }
}