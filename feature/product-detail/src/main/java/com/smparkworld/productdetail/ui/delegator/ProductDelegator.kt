package com.smparkworld.productdetail.ui.delegator

import com.smparkworld.domain.Result
import com.smparkworld.domain.dto.tmp.ProductDTO

interface ProductDelegator {

    suspend fun getProductById(id: Long): Result<ProductDTO>
}