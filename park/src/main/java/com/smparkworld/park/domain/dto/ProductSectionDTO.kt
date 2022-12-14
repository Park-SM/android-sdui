package com.smparkworld.park.domain.dto

import com.smparkworld.park.model.action.ClickableDTO
import com.smparkworld.park.model.action.WishClickableDTO

data class ProductSectionDTO(

    override var sectionType: String? = null,

    override var viewType: String? = null,

    var id: Long? = null,

    var imageUrl: String? = null,

    var title: String? = null,

    var category: String? = null,

    var reviewScore: String? = null,

    var price: String? = null,

    var linkUrl: String? = null,

    var isWished: Boolean? = null,

) : SectionDTO,
    ClickableDTO,
    WishClickableDTO {

    override fun getRedirectUrl(): String? {
        return linkUrl
    }

    override fun getWishTargetId(): Long? {
        return id
    }

    override fun getWishState(): Boolean? {
        return isWished
    }

    override fun setWishState(isWished: Boolean) {
        this.isWished = isWished
    }
}