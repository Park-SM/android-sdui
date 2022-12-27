package com.smparkworld.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParkRefreshVO(

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("id")
    val id: String? = null

) : Parcelable