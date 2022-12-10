package com.smparkworld.park.ui.model

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.park.data.vo.SectionVO
import com.smparkworld.park.domain.dto.SectionDTO
import kotlin.reflect.KClass

class SectionDiffCallback(
    private val viewBinders: Map<Class<out SectionDTO>, SectionViewBinder<SectionDTO, ViewHolder>>
) : DiffUtil.ItemCallback<SectionDTO>() {

    override fun areItemsTheSame(oldItem: SectionDTO, newItem: SectionDTO): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return viewBinders[oldItem::class.java]?.areItemsTheSame(oldItem, newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: SectionDTO, newItem: SectionDTO): Boolean {
        // We know the items are the same class because [areItemsTheSame] returned true
        return viewBinders[oldItem::class.java]?.areContentsTheSame(oldItem, newItem) ?: false
    }
}