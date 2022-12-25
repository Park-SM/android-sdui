package com.smparkworld.data.repository

import com.smparkworld.core.mapper.MapperManager
import com.smparkworld.data.source.remote.SectionRemoteDataSource
import com.smparkworld.data.vo.SectionVO
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.repository.SectionRepository
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val remoteDataSource: SectionRemoteDataSource,
    private val mapperManager: MapperManager
) : SectionRepository {

    override suspend fun requestSections(uri: String): ParkSectionsDTO {
        return remoteDataSource.requestSections(uri).let { vo ->
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