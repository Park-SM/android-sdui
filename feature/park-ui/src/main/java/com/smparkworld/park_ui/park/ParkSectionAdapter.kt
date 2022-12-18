package com.smparkworld.park_ui.park

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.park_ui.park.model.SectionDiffCallback
import com.smparkworld.park_ui.park.model.SectionViewBinder
import com.smparkworld.park_ui.park.model.SectionViewTypeKey

class ParkSectionAdapter(
    private val viewBinders: Map<String, SectionViewBinder<SectionDTO, ViewHolder>>
) : ListAdapter<SectionDTO, ViewHolder>(SectionDiffCallback(viewBinders)) {

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

    override fun onViewRecycled(holder: ViewHolder) {
        getViewBinder(holder.itemViewType).onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        getViewBinder(holder.itemViewType).onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }
}