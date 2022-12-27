package com.smparkworld.data.mapper

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ParkRefreshVO
import com.smparkworld.domain.dto.ParkRefreshDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkRefreshVOMapper @Inject constructor(

) : Mapper<ParkRefreshDTO, ParkRefreshVO>() {

    override fun map(from: ParkRefreshDTO): ParkRefreshVO {
        return ParkRefreshVO(
            type = from.type,
            id = from.id
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ParkRefreshDTO::class)
                && to.isAssignableFrom(ParkRefreshVO::class)
}