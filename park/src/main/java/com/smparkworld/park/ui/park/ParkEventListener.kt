package com.smparkworld.park.ui.park

import android.view.View
import com.smparkworld.park.ui.park.model.SectionItemEvent

interface ParkEventListener {

    fun onClickItem(v: View, event: SectionItemEvent)
}