package com.smparkworld.productdetail.ui.delegator

import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.ProductDetailDTO
import com.smparkworld.domain.usecase.GetProductByIdUseCase
import javax.inject.Inject

internal class ProductDefaultDelegator @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ProductDelegator {

    override suspend fun getProductById(id: Long): Result<ProductDetailDTO> {
        return getProductByIdUseCase(id)
    }
}