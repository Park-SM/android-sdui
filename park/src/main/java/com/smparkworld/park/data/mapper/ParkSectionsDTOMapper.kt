package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.hiltbinder.HiltSetBinds
import com.smparkworld.network.model.ParkSectionsVO
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltSetBinds
class ParkSectionsDTOMapper @Inject constructor(

) : Mapper<ParkSectionsDTO, ParkSectionsVO> {

    override fun map(from: ParkSectionsDTO): ParkSectionsVO {
        TODO("Not yet implemented")
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }
}