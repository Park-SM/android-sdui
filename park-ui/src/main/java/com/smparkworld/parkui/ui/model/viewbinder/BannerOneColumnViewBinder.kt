package com.smparkworld.parkui.ui.model.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.domain.dto.BannerSectionDTO
import com.smparkworld.park.R
import com.smparkworld.park.databinding.ParkSduiBannerOneColumnBinding
import com.smparkworld.parkui.ui.ParkEventListener
import com.smparkworld.parkui.ui.model.SectionItemEvent
import com.smparkworld.parkui.ui.model.SectionViewBinder
import javax.inject.Inject

class BannerOneColumnViewBinder @Inject constructor(

) : SectionViewBinder<BannerSectionDTO, BannerOneColumnViewHolder>(BannerSectionDTO::class) {

    override fun createViewHolder(
        parent: ViewGroup,
        owner: LifecycleOwner,
        listener: ParkEventListener
    ): RecyclerView.ViewHolder {
        return BannerOneColumnViewHolder(
            ParkSduiBannerOneColumnBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            owner, listener
        )
    }

    override fun bindViewHolder(model: BannerSectionDTO, viewHolder: BannerOneColumnViewHolder): Unit =
        viewHolder.bind(model)

    override fun getSectionItemType(): Int =
        R.layout.park_sdui_banner_one_column

    override fun areItemsTheSame(oldItem: BannerSectionDTO, newItem: BannerSectionDTO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BannerSectionDTO, newItem: BannerSectionDTO): Boolean =
        oldItem == newItem
}

class BannerOneColumnViewHolder(
    private val binding: ParkSduiBannerOneColumnBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: ParkEventListener
) : RecyclerView.ViewHolder(binding.root) {

    private val itemListener = object: BannerOneColumnItemListener {

        override fun onClickItem(v: View, model: BannerSectionDTO) {
            val linkUri = model.getRedirectUri() ?: return
            eventListener.onClickSection(v, SectionItemEvent.Click(linkUri))
        }
    }

    fun bind(model: BannerSectionDTO) {
        binding.lifecycleOwner = lifecycleOwner
        binding.model = model
        binding.listener = itemListener
    }

    interface BannerOneColumnItemListener {

        fun onClickItem(v: View, model: BannerSectionDTO)
    }
}