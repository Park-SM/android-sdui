package com.smparkworld.data.mapper

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ParkRequestUriVO
import com.smparkworld.domain.dto.ParkRequestUriDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ParkRequestDTOMapper @Inject constructor(

) : Mapper<ParkRequestUriVO, ParkRequestUriDTO>() {

    override fun map(from: ParkRequestUriVO): ParkRequestUriDTO {
        return ParkRequestUriDTO(
            nextPageUri = from.nextPageUri,
            nextPageTriggerPosition = from.nextPageTriggerPosition
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ParkRequestUriVO::class)
                && to.isAssignableFrom(ParkRequestUriDTO::class)
}