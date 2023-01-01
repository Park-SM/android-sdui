package com.smparkworld.core.ui.delegator

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.support.recyclerview.LoadStateFooterAdapter

interface BottomLoadStateDelegator {

    var footerAdapter: LoadStateFooterAdapter?

    fun withLoadStateFooter(
        originAdapter: RecyclerView.Adapter<ViewHolder>,
        footerAdapter: LoadStateFooterAdapter
    ): ConcatAdapter

    fun setLoadState(loadState: BottomLoadState)

    sealed class BottomLoadState {

        object IsNotLoading : BottomLoadState()

        object IsLoading : BottomLoadState()

        data class Error(
            val exception: Exception
        ): BottomLoadState()
    }
}