package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.vo.ParkSectionsVO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.extension.isAssignableFrom
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkSectionsDTOMapper @Inject constructor() : Mapper<ParkSectionsVO, ParkSectionsDTO>() {

    override fun map(from: ParkSectionsVO): ParkSectionsDTO {
        return ParkSectionsDTO(
            requestUrl = delegateMap(from.requestUrl),
            sections = delegateMaps(from.sections)
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ParkSectionsVO::class)
                && to.isAssignableFrom(ParkSectionsDTO::class)
}