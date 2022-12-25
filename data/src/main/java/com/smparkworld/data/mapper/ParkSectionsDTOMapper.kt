package com.smparkworld.data.mapper

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ParkSectionsVO
import com.smparkworld.domain.dto.ParkSectionsDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkSectionsDTOMapper @Inject constructor(

) : Mapper<ParkSectionsVO, ParkSectionsDTO>() {

    override fun map(from: ParkSectionsVO): ParkSectionsDTO {
        return ParkSectionsDTO(
            requestUri = delegateMap(from.requestUri),
            sections = delegateMaps(from.sections) ?: emptyList()
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ParkSectionsVO::class)
                && to.isAssignableFrom(ParkSectionsDTO::class)
}