package com.smparkworld.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.BannerSectionVO
import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.domain.dto.BannerSectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class BannerSectionVOMapper @Inject constructor(

) : Mapper<BannerSectionDTO, BannerSectionVO>() {

    override fun map(from: BannerSectionDTO): BannerSectionVO {
        return BannerSectionVO(
            sectionType = from.sectionType,
            viewType = from.viewType,
            refresh = delegateMap(from.refresh),
            id = from.id,
            title = from.title,
            message = from.message,
            imageUri = from.imageUri,
            linkUri = from.linkUri
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(BannerSectionDTO::class)
                && to.isAssignableFrom(BannerSectionVO::class)
}