package com.smparkworld.park.domain.usecase

import com.smparkworld.park.di.annotation.IoDispatcher
import com.smparkworld.park.domain.repository.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<Long, Boolean>(dispatcher) {

    override suspend fun execute(parameters: Long): Boolean {
        return wishRepository.deleteWish(parameters)
    }
}