package com.smparkworld.park.data.repository

import com.smparkworld.core.mapper.MapperManager
import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.data.source.remote.ParkRemoteDataSource
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.repository.ParkRepository
import javax.inject.Inject
import javax.inject.Singleton

@HiltBinds
@Singleton
class ParkRepositoryImpl @Inject constructor(
    private val remoteDataSource: ParkRemoteDataSource,
    private val mapperManager: MapperManager
) : ParkRepository {

    override suspend fun requestSections(url: String): ParkSectionsDTO {
        return remoteDataSource.requestSections().let { vo ->
            mapperManager.map(vo)
        }
    }
}