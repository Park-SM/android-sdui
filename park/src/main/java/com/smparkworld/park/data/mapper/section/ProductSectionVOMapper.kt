package com.smparkworld.park.data.mapper.section

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.vo.ProductSectionVO
import com.smparkworld.park.domain.dto.ProductSectionDTO
import com.smparkworld.park.extension.isAssignableFrom
import javax.inject.Inject
import kotlin.reflect.KClass

class ProductSectionVOMapper @Inject constructor(

) : Mapper<ProductSectionDTO, ProductSectionVO>() {

    override fun map(from: ProductSectionDTO): ProductSectionVO {
        return ProductSectionVO(
            sectionType = from.sectionType,
            viewType = from.viewType,
            id = from.id,
            imageUrl = from.imageUrl,
            title = from.title,
            category = from.category,
            reviewScore = from.reviewScore,
            price = from.price,
            isWished = from.isWished,
            linkUrl = from.linkUrl
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ProductSectionDTO::class)
                && to.isAssignableFrom(ProductSectionVO::class)
}