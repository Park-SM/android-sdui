package com.smparkworld.productdetail.ui.delegator

import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.ProductDetailDTO

internal interface ProductDelegator {

    suspend fun getProductById(id: Long): Result<ProductDetailDTO>
}