package com.smparkworld.park.ui.model

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.park.ui.EventListener
import kotlin.reflect.KClass

abstract class SectionViewBinder<M, in VH : ViewHolder>(
    val modelClass: KClass<*>
) : DiffUtil.ItemCallback<M>() {

    private lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var eventListener: EventListener

    fun initialize(owner: LifecycleOwner, listener: EventListener) {
        this.lifecycleOwner = owner
        this.eventListener = listener
    }

    fun createViewHolder(parent: ViewGroup): ViewHolder {
        if (::lifecycleOwner.isInitialized && ::eventListener.isInitialized) {
            return createViewHolder(parent, lifecycleOwner, eventListener)
        } else {
            throw IllegalStateException("ViewBinder is not initialized.")
        }
    }

    abstract fun createViewHolder(parent: ViewGroup, owner: LifecycleOwner, listener: EventListener): ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH)
    abstract fun getSectionItemType(): Int

    // Having these as non abstract because not all the viewBinders are required to implement them.
    open fun onViewRecycled(viewHolder: VH) = Unit
    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
}