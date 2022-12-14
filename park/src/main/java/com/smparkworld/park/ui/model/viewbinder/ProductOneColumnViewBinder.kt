package com.smparkworld.park.ui.model.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.park.R
import com.smparkworld.park.databinding.ParkSduiProductOneColumnBinding
import com.smparkworld.park.domain.dto.ProductSectionDTO
import com.smparkworld.park.ui.EventListener
import com.smparkworld.park.ui.model.SectionItemEvent
import com.smparkworld.park.ui.model.SectionViewBinder
import javax.inject.Inject

class ProductOneColumnViewBinder @Inject constructor(

) : SectionViewBinder<ProductSectionDTO, ProductOneColumnViewHolder>(ProductSectionDTO::class) {

    override fun createViewHolder(
        parent: ViewGroup,
        owner: LifecycleOwner,
        listener: EventListener
    ): RecyclerView.ViewHolder {
        return ProductOneColumnViewHolder(
            ParkSduiProductOneColumnBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            owner, listener
        )
    }

    override fun bindViewHolder(model: ProductSectionDTO, viewHolder: ProductOneColumnViewHolder): Unit =
        viewHolder.bind(model)

    override fun getSectionItemType(): Int =
        R.layout.park_sdui_product_one_column

    override fun areItemsTheSame(oldItem: ProductSectionDTO, newItem: ProductSectionDTO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductSectionDTO, newItem: ProductSectionDTO): Boolean =
        oldItem == newItem
}

class ProductOneColumnViewHolder(
    private val binding: ParkSduiProductOneColumnBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: EventListener
) : RecyclerView.ViewHolder(binding.root) {

    private val itemListener = object: ProductOneColumnItemListener {

        override fun onClickItem(v: View, model: ProductSectionDTO) {
            eventListener.onClickItem(v, SectionItemEvent.Click(model))
        }

        override fun onClickWish(v: ImageView, model: ProductSectionDTO) {
            eventListener.onClickItem(v, SectionItemEvent.WishClick(model, v.isSelected))
        }
    }

    fun bind(model: ProductSectionDTO) {
        binding.lifecycleOwner = lifecycleOwner
        binding.model = model
        binding.listener = itemListener
    }

    interface ProductOneColumnItemListener {

        fun onClickItem(v: View, model: ProductSectionDTO)

        fun onClickWish(v: ImageView, model: ProductSectionDTO)
    }
}