package com.smparkworld.core.ui.delegator

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.support.recyclerview.LoadStateFooterAdapter

class BottomLoadStateDefaultDelegator : BottomLoadStateDelegator {

    override var footerAdapter: LoadStateFooterAdapter? = null

    override fun withLoadStateFooter(
        originAdapter: RecyclerView.Adapter<ViewHolder>,
        footerAdapter: LoadStateFooterAdapter
    ): ConcatAdapter {
        this.footerAdapter = footerAdapter
        return ConcatAdapter(originAdapter, footerAdapter)
    }

    override fun setLoadState(loadState: BottomLoadStateDelegator.BottomLoadState) {
        footerAdapter?.loadState = loadState
    }
}