package com.smparkworld.park.ui

import android.view.View
import com.smparkworld.park.ui.model.SectionItemEvent

interface EventListener {

    fun onClickItem(v: View, event: SectionItemEvent)
}