package com.smparkworld.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.smparkworld.park.model.Section
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ParkSectionsVO(

    @SerializedName("requestUrl")
    val requestUrl: ParkRequestUrlVO? = null,

    @SerializedName("sections")
    val sections: List<@RawValue Section>? = null,

) : Parcelable
