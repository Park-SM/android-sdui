package com.smparkworld.parkui.ui.model

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.domain.dto.SectionDTO

class SectionDiffCallback(
    viewBinders: Map<String, SectionViewBinder<SectionDTO, ViewHolder>>
) : DiffUtil.ItemCallback<SectionDTO>() {

    private val modelClassToBinders = viewBinders.mapKeys { it.value.modelClass }

    override fun areItemsTheSame(oldItem: SectionDTO, newItem: SectionDTO): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return modelClassToBinders[oldItem::class]?.areItemsTheSame(oldItem, newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: SectionDTO, newItem: SectionDTO): Boolean {
        // We know the items are the same class because [areItemsTheSame] returned true
        return modelClassToBinders[oldItem::class]?.areContentsTheSame(oldItem, newItem) ?: false
    }
}