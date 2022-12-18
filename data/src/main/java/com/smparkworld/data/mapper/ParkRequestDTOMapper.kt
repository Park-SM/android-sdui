package com.smparkworld.data.mapper

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ParkRequestUrlVO
import com.smparkworld.domain.dto.ParkRequestUrlDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkRequestDTOMapper @Inject constructor(

) : Mapper<ParkRequestUrlVO, ParkRequestUrlDTO>() {

    override fun map(from: ParkRequestUrlVO): ParkRequestUrlDTO {
        return ParkRequestUrlDTO(
            nextPageUrl = from.nextPageUrl,
            nextPageTriggerPosition = from.nextPageTriggerPosition
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ParkRequestUrlVO::class)
                && to.isAssignableFrom(ParkRequestUrlDTO::class)
}