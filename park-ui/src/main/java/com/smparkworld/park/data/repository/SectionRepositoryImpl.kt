package com.smparkworld.park.data.repository

import com.smparkworld.core.mapper.MapperManager
import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.data.source.remote.SectionRemoteDataSource
import com.smparkworld.park.data.vo.SectionVO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.repository.SectionRepository
import javax.inject.Inject
import javax.inject.Singleton

@HiltBinds
@Singleton
class SectionRepositoryImpl @Inject constructor(
    private val remoteDataSource: SectionRemoteDataSource,
    private val mapperManager: MapperManager
) : SectionRepository {

    override suspend fun requestSections(url: String): ParkSectionsDTO {
        return remoteDataSource.requestSections(url).let { vo ->
            mapperManager.map(vo)
        }
    }

    override suspend fun cloneSection(section: SectionDTO): SectionDTO {
         val payload = mapperManager.map<SectionDTO, SectionVO>(section)
         return remoteDataSource.cloneSection(payload).let { vo ->
             mapperManager.map(vo)
         }
    }
}