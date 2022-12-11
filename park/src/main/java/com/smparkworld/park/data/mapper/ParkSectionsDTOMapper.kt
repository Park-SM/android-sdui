package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.vo.ParkSectionsVO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.cast
import kotlin.reflect.safeCast

class ParkSectionsDTOMapper @Inject constructor() : Mapper<ParkSectionsVO, ParkSectionsDTO>() {

    override fun map(from: ParkSectionsVO): ParkSectionsDTO {
        return ParkSectionsDTO(
            requestUrl = delegateMap(from.requestUrl),
            sections = delegateMaps(from.sections)
        )
    }

    override fun equals(from: Class<*>, to: Class<*>): Boolean =
        from == ParkSectionsVO::class.java && to == ParkSectionsDTO::class.java
}