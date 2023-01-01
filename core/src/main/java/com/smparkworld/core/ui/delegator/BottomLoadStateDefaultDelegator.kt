package com.smparkworld.core.ui.delegator

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.support.recyclerview.BottomLoadState
import com.smparkworld.core.ui.support.recyclerview.BottomLoadStateAdapter

class BottomLoadStateDefaultDelegator : BottomLoadStateDelegator {

    override var bottomAdapter: BottomLoadStateAdapter? = null

    override fun withBottomLoadState(
        originAdapter: RecyclerView.Adapter<ViewHolder>,
        bottomAdapter: BottomLoadStateAdapter
    ): ConcatAdapter {
        this.bottomAdapter = bottomAdapter
        return ConcatAdapter(originAdapter, bottomAdapter)
    }

    override fun setBottomLoadState(loadState: BottomLoadState) {
        bottomAdapter?.loadState = loadState
    }
}