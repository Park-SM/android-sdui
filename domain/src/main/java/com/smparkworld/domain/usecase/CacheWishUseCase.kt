package com.smparkworld.domain.usecase

import com.smparkworld.core_di.IoDispatcher
import com.smparkworld.domain.repository.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

// Assume that all products are managed with one ID system.
// If the ID system is divided by product domain,
// the `SectionRepository` and `WishRepository` classes can
// be combined into one.
class CacheWishUseCase @Inject constructor(
    private val wishRepository: WishRepository,
    @IoDispatcher dispatcher : CoroutineDispatcher
) : UseCaseWithParam<CacheWishUseCase.Payload, Unit>(dispatcher){

    override suspend fun execute(parameters: Payload) {
        wishRepository.cacheWishState(parameters.id, parameters.isWished)
    }

    data class Payload(
        val id: Long,
        val isWished: Boolean
    )
}