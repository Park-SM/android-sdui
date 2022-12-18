package com.smparkworld.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ParkSectionsVO(

    @SerializedName("requestUrl")
    val requestUrl: ParkRequestUrlVO? = null,

    @SerializedName("sections")
    val sections: List<@RawValue SectionVO>? = null,

) : Parcelable