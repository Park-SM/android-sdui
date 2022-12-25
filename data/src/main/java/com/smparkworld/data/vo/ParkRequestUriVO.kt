package com.smparkworld.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParkRequestUriVO(

    @SerializedName("nextPageUri")
    val nextPageUri: String? = null,

    @SerializedName("nextPageTriggerPosition")
    val nextPageTriggerPosition: Int? = null

) : Parcelable