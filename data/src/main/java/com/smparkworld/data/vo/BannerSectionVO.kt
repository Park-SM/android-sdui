package com.smparkworld.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerSectionVO(

    @SerializedName("sectionType")
    override val sectionType: String? = null,

    @SerializedName("viewType")
    override val viewType: String? = null,

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("imageUri")
    val imageUri: String? = null,

    @SerializedName("linkUri")
    val linkUri: String? = null

) : SectionVO, Parcelable
