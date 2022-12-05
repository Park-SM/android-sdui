package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.core.mapper.MapperDispatcher
import com.smparkworld.hiltbinder.HiltSetBinds
import com.smparkworld.park.data.vo.ParkRequestUrlVO
import com.smparkworld.park.data.vo.ParkSectionsVO
import com.smparkworld.park.data.vo.SectionVO
import com.smparkworld.park.domain.dto.ParkRequestUrlDTO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.dto.SectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltSetBinds
class ParkSectionsDTOMapper @Inject constructor(
    private val dispatcher: MapperDispatcher
) : Mapper<ParkSectionsVO, ParkSectionsDTO> {

    override fun map(from: ParkSectionsVO): ParkSectionsDTO {
        return ParkSectionsDTO(
            requestUrl = from.requestUrl?.let { vo ->
                dispatcher.getMapper<ParkRequestUrlVO, ParkRequestUrlDTO>().map(vo)
            },
            sections = from.sections?.map { vo ->
                dispatcher.getMapper<SectionVO, SectionDTO>().map(vo)
            }
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from == ParkSectionsVO::class && to == ParkSectionsDTO::class
}