package com.smparkworld.core.ui.support.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.delegator.BottomLoadStateDelegator.BottomLoadState

abstract class LoadStateFooterAdapter: RecyclerView.Adapter<ViewHolder>() {

    var loadState: BottomLoadState = BottomLoadState.IsNotLoading
        set(value) {
            val oldState = displayLoadStateAsItem(field)
            val newState = displayLoadStateAsItem(value)

            when {
                (oldState && !newState) -> {
                    notifyItemRemoved(0)
                }
                (oldState && newState) -> {
                    notifyItemChanged(0)
                }
                (!oldState && newState) -> {
                    notifyItemInserted(0)
                }
            }
            field = value
        }

    final override fun getItemCount(): Int = if (displayLoadStateAsItem(loadState)) 1 else 0

    final override fun getItemViewType(position: Int): Int = getStateViewType(loadState)

    open fun getStateViewType(loadState: BottomLoadState): Int = 0

    open fun displayLoadStateAsItem(state: BottomLoadState): Boolean {
        return state is BottomLoadState.IsLoading || state is BottomLoadState.Error
    }
}