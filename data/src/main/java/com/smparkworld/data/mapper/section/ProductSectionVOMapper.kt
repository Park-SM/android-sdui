package com.smparkworld.data.mapper.section

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ProductSectionVO
import com.smparkworld.domain.dto.ProductSectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ProductSectionVOMapper @Inject constructor(

) : Mapper<ProductSectionDTO, ProductSectionVO>() {

    override fun map(from: ProductSectionDTO): ProductSectionVO {
        return ProductSectionVO(
            sectionType = from.sectionType,
            viewType = from.viewType,
            id = from.id,
            imageUri = from.imageUri,
            title = from.title,
            category = from.category,
            reviewScore = from.reviewScore,
            price = from.price,
            isWished = from.isWished,
            linkUri = from.linkUri
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ProductSectionDTO::class)
                && to.isAssignableFrom(ProductSectionVO::class)
}