package com.smparkworld.park.data.vo

import com.google.gson.annotations.SerializedName

abstract class SectionVO {

    @SerializedName("type")
    val viewType: String? = null

    @SerializedName("sectionType")
    val sectionType: String? = null
}