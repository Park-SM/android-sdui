package com.smparkworld.park.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParkRequestUrlVO(

    @SerializedName("nextPageUrl")
    val nextPageUrl: String? = null,

    @SerializedName("nextPageTriggerPosition")
    val nextPageTriggerPosition: Int? = null

) : Parcelable