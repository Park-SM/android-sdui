package com.smparkworld.parkui.ui

import android.view.View
import com.smparkworld.parkui.ui.model.SectionItemEvent

interface ParkEventListener {

    fun onClickSection(v: View, event: SectionItemEvent)
}