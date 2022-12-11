package com.smparkworld.park.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerSectionVO(

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("linkUrl")
    val linkUrl: String? = null

) : SectionVO(), Parcelable
