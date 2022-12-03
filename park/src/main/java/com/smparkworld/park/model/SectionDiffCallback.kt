package com.smparkworld.park.model

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.reflect.KClass

class SectionDiffCallback(
    private val viewBinders: Map<KClass<out Section>, SectionViewBinder<Section, ViewHolder>>
) : DiffUtil.ItemCallback<Section>() {

    override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return viewBinders[oldItem::class]?.areItemsTheSame(oldItem, newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
        // We know the items are the same class because [areItemsTheSame] returned true
        return viewBinders[oldItem::class]?.areContentsTheSame(oldItem, newItem) ?: false
    }
}