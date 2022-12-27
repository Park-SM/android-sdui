package com.smparkworld.data.mapper

import com.smparkworld.core.extension.isAssignableFrom
import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.vo.ProductDetailVO
import com.smparkworld.domain.dto.ProductDetailDTO
import javax.inject.Inject
import kotlin.reflect.KClass

class ProductDetailDTOMapper @Inject constructor(

) : Mapper<ProductDetailVO, ProductDetailDTO>() {


    override fun map(from: ProductDetailVO): ProductDetailDTO {
        return ProductDetailDTO(
            id = from.id,
            imageUri = from.imageUri,
            title = from.title,
            category = from.category,
            reviewScore = from.reviewScore,
            price = from.price,
            isWished = from.isWished
        )
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from.isAssignableFrom(ProductDetailVO::class)
                && to.isAssignableFrom(ProductDetailDTO::class)
}