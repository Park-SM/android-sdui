package com.smparkworld.park.data.mapper.section

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.vo.ProductSectionVO
import com.smparkworld.park.domain.dto.ProductSectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ProductSectionDTOMapper @Inject constructor() : Mapper<ProductSectionVO, ProductSectionDTO>() {

    override fun map(from: ProductSectionVO): ProductSectionDTO {
        return ProductSectionDTO(
            id = from.id,
            imageUrl = from.imageUrl,
            title = from.title,
            category = from.category,
            reviewScore = from.reviewScore,
            price = from.price,
            linkUrl = from.linkUrl
        )
    }

    override fun equals(from: Class<*>, to: Class<*>): Boolean =
        from.isAssignableFrom(ProductSectionVO::class.java)
                && to.isAssignableFrom(ProductSectionDTO::class.java)
}