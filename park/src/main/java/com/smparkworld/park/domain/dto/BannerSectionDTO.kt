package com.smparkworld.park.domain.dto

import android.os.Parcelable
import com.smparkworld.park.model.action.ClickableDTO
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerSectionDTO(

    override var sectionType: String? = null,

    override var viewType: String? = null,

    var id: Long? = null,

    var title: String? = null,

    var message: String? = null,

    var imageUrl: String? = null,

    var linkUrl: String? = null,

) : SectionDTO,
    Parcelable,
    ClickableDTO {

    override fun getRedirectUrl(): String? {
        return linkUrl
    }
}
