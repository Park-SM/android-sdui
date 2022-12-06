package com.smparkworld.park.data.mapper.section

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.hiltbinder.HiltSetBinds
import com.smparkworld.park.data.vo.ProductSectionVO
import com.smparkworld.park.data.vo.SectionVO
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.model.sections.ProductSectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltSetBinds
class ProductSectionDTOMapper @Inject constructor() : Mapper<ProductSectionVO, ProductSectionDTO>() {

    override fun map(from: ProductSectionVO): ProductSectionDTO {
        return ProductSectionDTO(
            id = from.id,
            imageUrl = from.imageUrl,
            title = from.title,
            category = from.category,
            reviewScore = from.reviewScore,
            price = from.price
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from == SectionVO::class && to == SectionDTO::class
}