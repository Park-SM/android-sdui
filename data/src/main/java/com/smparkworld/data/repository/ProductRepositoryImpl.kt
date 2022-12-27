package com.smparkworld.data.repository

import com.smparkworld.core.mapper.MapperManager
import com.smparkworld.data.source.remote.ProductRemoteDataSource
import com.smparkworld.domain.dto.ProductDetailDTO
import com.smparkworld.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val mapperManager: MapperManager
) : ProductRepository {

    override suspend fun getProductById(id: Long): ProductDetailDTO {
        return remoteDataSource.getProductById(id).let { vo ->
            mapperManager.map(vo)
        }
    }
}