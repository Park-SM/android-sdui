package com.smparkworld.park.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.park.model.Section
import com.smparkworld.park.model.SectionDiffCallback
import com.smparkworld.park.model.SectionViewBinder
import kotlin.reflect.KClass

class ParkSectionAdapter(
    viewBinders: Map<KClass<out Section>, SectionViewBinder<Section, ViewHolder>>
) : ListAdapter<Section, ViewHolder>(SectionDiffCallback(viewBinders)) {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getFeedItemType() }

    private fun getViewBinder(viewType: Int): SectionViewBinder<Section, ViewHolder> =
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