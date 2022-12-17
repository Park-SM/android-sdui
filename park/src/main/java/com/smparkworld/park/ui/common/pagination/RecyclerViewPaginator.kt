package com.smparkworld.park.ui.common.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPaginator private constructor(
    view: RecyclerView,
    listener: ((Int) -> Unit)? = null,
) : ScrollingViewPaginator {

    private var currentPage = 0
    private var triggerPosition: Int? = null

    init {
        with(view) {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy <= 0) return
                    val triggerPosition = triggerPosition ?: return

                    val lastVisibleItemPosition = (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition() ?: -1
                    val totalItemCount = layoutManager?.itemCount ?: 0

                    val triggered = (totalItemCount - triggerPosition) <= lastVisibleItemPosition
                    if (triggered) {
                        listener?.invoke(++currentPage)
                    }
                }
            })
        }
    }

    override fun setNextPageTriggerPosition(position: Int?) {
        triggerPosition = position
    }

    class RecyclerViewPaginatorBuilder(
        private val view: RecyclerView
    ) : ScrollingViewPaginator.Builder {

        private var nextPageTriggerPosition: Int? = null
        private var onNextPageListener: ((Int) -> Unit)? = null

        override fun setNextPageTriggerPosition(position: Int): ScrollingViewPaginator.Builder {
            nextPageTriggerPosition = position
            return this
        }

        override fun setOnNextPageListener(listener: (nextPage: Int) -> Unit): ScrollingViewPaginator.Builder {
            onNextPageListener = listener
            return this
        }

        override fun create(): ScrollingViewPaginator {
            return RecyclerViewPaginator(view, onNextPageListener).apply {
                setNextPageTriggerPosition(nextPageTriggerPosition)
            }
        }
    }
}