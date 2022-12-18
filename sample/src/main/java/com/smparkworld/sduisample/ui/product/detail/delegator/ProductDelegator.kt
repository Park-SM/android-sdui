package com.smparkworld.sduisample.ui.product.detail.delegator

import com.smparkworld.core.model.Result
import com.smparkworld.sduisample.domain.dto.ProductDTO

interface ProductDelegator {

    suspend fun getProductById(id: Long): Result<ProductDTO>
}