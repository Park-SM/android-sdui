package com.smparkworld.park.domain.dto

import com.smparkworld.core.RequestUrl
import com.smparkworld.park.model.action.ClickableDTO
import com.smparkworld.park.model.action.WishClickableDTO

data class ProductSectionDTO(

    var id: Long? = null,

    var imageUrl: String? = null,

    var title: String? = null,

    var category: String? = null,

    var reviewScore: String? = null,

    var price: String? = null,

    var linkUrl: String? = null,

) : SectionDTO(),
    ClickableDTO,
    WishClickableDTO {

    override fun getRedirectUrl(): String? {
        return linkUrl
    }

    override fun getWishRequestUrl(isWished: Boolean): String {
        return RequestUrl.WISH_REQUEST_URL
    }
}