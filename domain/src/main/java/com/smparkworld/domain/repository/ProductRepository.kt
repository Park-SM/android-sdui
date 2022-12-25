package com.smparkworld.domain.repository

import com.smparkworld.domain.dto.ProductDetailDTO

interface ProductRepository {

    suspend fun getProductById(id: Long): ProductDetailDTO
}