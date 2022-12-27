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

    override suspend fun cloneSection(sectionDTO: SectionDTO): SectionDTO {
         val payload = mapperManager.map<SectionDTO, SectionVO>(sectionDTO)
         return remoteDataSource.cloneSection(payload).let { sectionVO ->
             mapperManager.map(sectionVO)
         }
    }

    override suspend fun requestPartialUpdateSections(sectionDTOs: List<SectionDTO>): List<SectionDTO> {
        val payload = mapperManager.mapList<SectionDTO, SectionVO>(sectionDTOs)
        return remoteDataSource.requestPartialUpdateSections(payload).let { sectionVOs ->
            mapperManager.mapList(sectionVOs)
        }
    }
}