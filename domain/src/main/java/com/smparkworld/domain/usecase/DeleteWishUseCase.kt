package com.smparkworld.domain.usecase

import com.smparkworld.coredi.IoDispatcher
import com.smparkworld.domain.repository.WishRepository
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