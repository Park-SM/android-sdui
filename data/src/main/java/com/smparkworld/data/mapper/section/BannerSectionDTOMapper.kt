package com.smparkworld.data.mapper.section

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.BannerSectionVO
import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.domain.dto.BannerSectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class BannerSectionDTOMapper @Inject constructor(

) : Mapper<BannerSectionVO, BannerSectionDTO>() {

    override fun map(from: BannerSectionVO): BannerSectionDTO {
        return BannerSectionDTO(
            sectionType = from.sectionType,
            viewType = from.viewType,
            id = from.id,
            title = from.title,
            message = from.message,
            imageUrl = from.imageUrl,
            linkUrl = from.linkUrl
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(BannerSectionVO::class)
                && to.isAssignableFrom(BannerSectionDTO::class)
}