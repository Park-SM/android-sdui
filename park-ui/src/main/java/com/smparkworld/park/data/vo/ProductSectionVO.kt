package com.smparkworld.park.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductSectionVO(

    @SerializedName("sectionType")
    override val sectionType: String? = null,

    @SerializedName("viewType")
    override val viewType: String? = null,

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("reviewScore")
    val reviewScore: String? = null,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("isWished")
    val isWished: Boolean? = null,

    @SerializedName("linkUrl")
    val linkUrl: String? = null

) : SectionVO, Parcelable