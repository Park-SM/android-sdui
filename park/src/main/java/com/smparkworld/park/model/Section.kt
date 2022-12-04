package com.smparkworld.park.model

import com.google.gson.annotations.SerializedName

abstract class Section {

    @SerializedName("type")
    val viewType: String? = null

    @SerializedName("sectionType")
    val sectionType: String? = null
}