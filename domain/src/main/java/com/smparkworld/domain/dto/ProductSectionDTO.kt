package com.smparkworld.domain.dto

import com.smparkworld.domain.dto.action.ClickableDTO
import com.smparkworld.domain.dto.action.WishClickableDTO

data class ProductSectionDTO(

    override var sectionType: String? = null,

    override var viewType: String? = null,

    var id: Long? = null,

    var imageUri: String? = null,

    var title: String? = null,

    var category: String? = null,

    var reviewScore: String? = null,

    var price: String? = null,

    var linkUri: String? = null,

    var isWished: Boolean? = null,

) : SectionDTO,
    ClickableDTO,
    WishClickableDTO {

    override fun getRedirectUri(): String? {
        return linkUri
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