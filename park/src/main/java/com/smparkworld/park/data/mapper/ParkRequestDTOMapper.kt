package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.vo.ParkRequestUrlVO
import com.smparkworld.park.domain.dto.ParkRequestUrlDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkRequestDTOMapper @Inject constructor() : Mapper<ParkRequestUrlVO, ParkRequestUrlDTO>() {

    override fun map(from: ParkRequestUrlVO): ParkRequestUrlDTO {
        return ParkRequestUrlDTO(
            nextPageUrl = from.nextPageUrl,
            nextPageTriggerPosition = from.nextPageTriggerPosition
        )
    }

    override fun equals(from: Class<*>, to: Class<*>): Boolean =
        from == ParkRequestUrlVO::class.java && to == ParkRequestUrlDTO::class.java
}