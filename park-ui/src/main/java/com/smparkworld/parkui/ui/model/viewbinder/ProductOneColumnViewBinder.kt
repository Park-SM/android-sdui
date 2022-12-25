package com.smparkworld.parkui.ui.model.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.domain.dto.ProductSectionDTO
import com.smparkworld.park.R
import com.smparkworld.park.databinding.ParkSduiProductOneColumnBinding
import com.smparkworld.parkui.ui.ParkEventListener
import com.smparkworld.parkui.ui.model.SectionItemEvent
import com.smparkworld.parkui.ui.model.SectionViewBinder
import javax.inject.Inject

class ProductOneColumnViewBinder @Inject constructor(

) : SectionViewBinder<ProductSectionDTO, ProductOneColumnViewHolder>(ProductSectionDTO::class) {

    override fun createViewHolder(
        parent: ViewGroup,
        owner: LifecycleOwner,
        listener: ParkEventListener
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
    private val eventListener: ParkEventListener
) : RecyclerView.ViewHolder(binding.root) {

    private val itemListener = object: ProductOneColumnItemListener {

        override fun onClickItem(v: View, model: ProductSectionDTO) {
            val linkUri = model.getRedirectUri() ?: return
            eventListener.onClickSection(v, SectionItemEvent.Click(linkUri))
        }

        override fun onClickWish(v: View, model: ProductSectionDTO) {
            val id = model.id ?: return
            val isSelected = v.isSelected.not().also {
                v.isSelected = it
                model.isWished = it
            }
            eventListener.onClickSection(v, SectionItemEvent.WishClick(id, isSelected))
        }
    }

    fun bind(model: ProductSectionDTO) {
        binding.lifecycleOwner = lifecycleOwner
        binding.model = model
        binding.listener = itemListener
    }

    interface ProductOneColumnItemListener {

        fun onClickItem(v: View, model: ProductSectionDTO)

        fun onClickWish(v: View, model: ProductSectionDTO)
    }
}