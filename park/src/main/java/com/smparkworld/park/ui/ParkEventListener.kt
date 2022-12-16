package com.smparkworld.park.ui

import android.view.View
import com.smparkworld.park.ui.model.SectionItemEvent

interface ParkEventListener {

    fun onClickItem(v: View, event: SectionItemEvent)
}