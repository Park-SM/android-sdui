package com.smparkworld.parkui.ui

import android.view.View
import com.smparkworld.parkui.ui.model.SectionItemEvent

interface ParkEventListener {

    fun onClickItem(v: View, event: SectionItemEvent)
}