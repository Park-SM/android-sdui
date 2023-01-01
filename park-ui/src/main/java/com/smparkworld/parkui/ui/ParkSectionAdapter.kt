package com.smparkworld.parkui.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.delegator.BottomLoadStateDefaultDelegator
import com.smparkworld.core.ui.delegator.BottomLoadStateDelegator
import com.smparkworld.core.ui.support.recyclerview.BottomLoadStateAdapter
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.parkui.ui.model.SectionDiffCallback
import com.smparkworld.parkui.ui.model.SectionViewBinder
import com.smparkworld.parkui.ui.model.SectionViewTypeKey

class ParkSectionAdapter(
    private val viewBinders: Map<String, SectionViewBinder<SectionDTO, ViewHolder>>
) : ListAdapter<SectionDTO, ViewHolder>(SectionDiffCallback(viewBinders)),
    BottomLoadStateDelegator by BottomLoadStateDefaultDelegator() {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getSectionItemType() }

    private fun getViewBinder(viewType: Int): SectionViewBinder<SectionDTO, ViewHolder> =
        viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int =
        (super.getItem(position).viewType ?: SectionViewTypeKey.DEFAULT_VIEW_TYPE).let { viewType ->
            viewBinders.getValue(viewType).getSectionItemType()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewBinder(viewType).createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return getViewBinder(getItemViewType(position)).bindViewHolder(getItem(position), holder)
    }

    fun withBottomLoadState(
        footerAdapter: BottomLoadStateAdapter
    ): ConcatAdapter {
        return withBottomLoadState(this, footerAdapter)
    }
}