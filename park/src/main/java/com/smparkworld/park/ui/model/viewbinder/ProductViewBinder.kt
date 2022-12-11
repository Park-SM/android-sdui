package com.smparkworld.park.ui.model.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.hiltbinder.HiltMapBinds
import com.smparkworld.park.R
import com.smparkworld.park.databinding.ParkSduiProductBinding
import com.smparkworld.park.di.annotation.SectionViewBinderKey
import com.smparkworld.park.di.annotation.SectionViewBinders
import com.smparkworld.park.domain.dto.ProductSectionDTO
import com.smparkworld.park.ui.EventListener
import com.smparkworld.park.ui.model.SectionItemEvent
import com.smparkworld.park.ui.model.SectionViewBinder
import javax.inject.Inject

@HiltMapBinds
@SectionViewBinders
@SectionViewBinderKey(ProductSectionDTO::class)
class ProductViewBinder @Inject constructor(
    // Inject viewTypeDispatcher..
) : SectionViewBinder<ProductSectionDTO, ProductViewHolder>(ProductSectionDTO::class) {

    override fun createViewHolder(
        parent: ViewGroup,
        owner: LifecycleOwner,
        listener: EventListener
    ): RecyclerView.ViewHolder {
        return ProductViewHolder(
            ParkSduiProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            owner, listener
        )
    }

    override fun bindViewHolder(model: ProductSectionDTO, viewHolder: ProductViewHolder): Unit =
        viewHolder.bind(model)

    override fun getSectionItemType(): Int =
        R.layout.park_sdui_product

    override fun areItemsTheSame(oldItem: ProductSectionDTO, newItem: ProductSectionDTO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductSectionDTO, newItem: ProductSectionDTO): Boolean =
        oldItem == newItem
}

class ProductViewHolder(
    private val binding: ParkSduiProductBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: EventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ProductSectionDTO) {
        binding.lifecycleOwner = lifecycleOwner
        binding.model = model
        binding.listener = object: ProductItemListener {

            override fun onClickItem(v: View) {
                eventListener.onClickItem(v, SectionItemEvent.Click(model))
            }

            override fun onClickWish(v: ImageView) {
                eventListener.onClickItem(v, SectionItemEvent.WishClick(model, v.isSelected))
            }
        }
    }

    interface ProductItemListener {

        fun onClickItem(v: View)

        fun onClickWish(v: ImageView)
    }
}