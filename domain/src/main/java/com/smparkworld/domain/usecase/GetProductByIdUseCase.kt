package com.smparkworld.domain.usecase

import com.smparkworld.qualifier.IoDispatcher
import com.smparkworld.domain.dto.ProductDetailDTO
import com.smparkworld.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<Long, ProductDetailDTO>(dispatcher) {

    override suspend fun execute(parameters: Long): ProductDetailDTO {
        return productRepository.getProductById(parameters)
    }
}