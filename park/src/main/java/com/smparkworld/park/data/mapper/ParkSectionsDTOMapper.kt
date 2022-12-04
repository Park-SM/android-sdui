package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.hiltbinder.HiltSetBinds
import com.smparkworld.network.model.ParkSectionsVO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltSetBinds
class ParkSectionsDTOMapper @Inject constructor(

) : Mapper<ParkSectionsVO, ParkSectionsDTO> {

    override fun map(from: ParkSectionsVO): ParkSectionsDTO {
        return ParkSectionsDTO(
            requestUrl = from.requestUrl,
            sections = from.sections
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from == ParkSectionsVO::class && to == ParkSectionsDTO::class
}