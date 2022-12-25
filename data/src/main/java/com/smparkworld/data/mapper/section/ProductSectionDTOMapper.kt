package com.smparkworld.data.mapper.section

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ProductSectionVO
import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.domain.dto.ProductSectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ProductSectionDTOMapper @Inject constructor(

) : Mapper<ProductSectionVO, ProductSectionDTO>() {

    override fun map(from: ProductSectionVO): ProductSectionDTO {
        return ProductSectionDTO(
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
        from.isAssignableFrom(ProductSectionVO::class)
                && to.isAssignableFrom(ProductSectionDTO::class)
}