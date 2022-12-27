package com.smparkworld.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailVO(

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("imageUri")
    val imageUri: String? = null,

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

) : Parcelable