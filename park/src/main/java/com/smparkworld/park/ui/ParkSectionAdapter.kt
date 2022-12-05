package com.smparkworld.park.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.park.data.vo.SectionVO
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.ui.model.SectionDiffCallback
import com.smparkworld.park.ui.model.SectionViewBinder
import kotlin.reflect.KClass

class ParkSectionAdapter(
    viewBinders: Map<KClass<out SectionDTO>, SectionViewBinder<SectionDTO, ViewHolder>>
) : ListAdapter<SectionDTO, ViewHolder>(SectionDiffCallback(viewBinders)) {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getFeedItemType() }

    private fun getViewBinder(viewType: Int): SectionViewBinder<SectionDTO, ViewHolder> =
        viewTypeToBinders.getValue(viewType)

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