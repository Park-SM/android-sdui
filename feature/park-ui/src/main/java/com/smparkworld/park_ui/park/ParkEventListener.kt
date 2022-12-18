package com.smparkworld.park_ui.park

import android.view.View
import com.smparkworld.park_ui.park.model.SectionItemEvent

interface ParkEventListener {

    fun onClickItem(v: View, event: SectionItemEvent)
}