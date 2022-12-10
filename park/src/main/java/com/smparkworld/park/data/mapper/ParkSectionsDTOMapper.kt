package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.vo.ParkSectionsVO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkSectionsDTOMapper @Inject constructor() : Mapper<ParkSectionsVO, ParkSectionsDTO>() {

    override fun map(from: ParkSectionsVO): ParkSectionsDTO {
        return ParkSectionsDTO(
            requestUrl = from.requestUrl?.let { vo ->
                mapperManagerLazy.get().map(vo)
            },
            sections = from.sections?.map { vo ->
                mapperManagerLazy.get().map(vo)
            }
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from == ParkSectionsVO::class && to == ParkSectionsDTO::class
}