package com.smparkworld.core.ui.support.pagination

import androidx.recyclerview.widget.RecyclerView

interface ScrollingViewPaginator {

    fun setNextPageTriggerPosition(position: Int?)

    companion object {

        @JvmStatic
        fun with(recyclerView: RecyclerView): Builder {
            return RecyclerViewPaginator.RecyclerViewPaginatorBuilder(recyclerView)
        }
    }

    interface Builder {

        fun setOnNextPageListener(listener: (nextPage: Int) -> Unit): Builder

        fun setNextPageTriggerPosition(position: Int?): Builder

        fun create(): ScrollingViewPaginator
    }
}