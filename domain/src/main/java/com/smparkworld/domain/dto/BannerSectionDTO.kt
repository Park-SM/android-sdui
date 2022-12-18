package com.smparkworld.domain.dto

import com.smparkworld.domain.dto.action.ClickableDTO

data class BannerSectionDTO(

    override var sectionType: String? = null,

    override var viewType: String? = null,

    var id: Long? = null,

    var title: String? = null,

    var message: String? = null,

    var imageUrl: String? = null,

    var linkUrl: String? = null,

) : SectionDTO,
    ClickableDTO {

    override fun getRedirectUrl(): String? {
        return linkUrl
    }
}
