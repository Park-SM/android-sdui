package com.smparkworld.core.ui.delegator

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.support.recyclerview.BottomLoadState
import com.smparkworld.core.ui.support.recyclerview.BottomLoadStateAdapter

interface BottomLoadStateDelegator {

    var bottomAdapter: BottomLoadStateAdapter?

    fun withBottomLoadState(
        originAdapter: RecyclerView.Adapter<ViewHolder>,
        bottomAdapter: BottomLoadStateAdapter
    ): ConcatAdapter

    fun setBottomLoadState(loadState: BottomLoadState)

}