package com.smparkworld.data.mapper

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ParkRefreshVO
import com.smparkworld.domain.dto.ParkRefreshDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkRefreshDTOMapper @Inject constructor(

) : Mapper<ParkRefreshVO, ParkRefreshDTO>() {

    override fun map(from: ParkRefreshVO): ParkRefreshDTO {
        return ParkRefreshDTO(
            type = from.type,
            id = from.id
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ParkRefreshVO::class)
                && to.isAssignableFrom(ParkRefreshDTO::class)
}