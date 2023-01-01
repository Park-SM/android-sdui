package com.smparkworld.parkui.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smparkworld.core.ui.delegator.BottomLoadStateDelegator.BottomLoadState
import com.smparkworld.core.ui.support.recyclerview.LoadStateFooterAdapter
import com.smparkworld.park.R
import com.smparkworld.park.databinding.ParkSduiLoadStateErrorBinding
import com.smparkworld.park.databinding.ParkSduiLoadStateLoadingBinding

class ParkSectionLoadStateAdapter(
    private val retryMoreSection: () -> Unit
) : LoadStateFooterAdapter() {

    override fun getStateViewType(loadState: BottomLoadState): Int {
        return when (loadState) {
            is BottomLoadState.IsLoading -> R.layout.park_sdui_load_state_loading
            is BottomLoadState.Error -> R.layout.park_sdui_load_state_error
            else -> View.NO_ID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.park_sdui_load_state_loading -> {
                LoadingViewHolder(
                    ParkSduiLoadStateLoadingBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            R.layout.park_sdui_load_state_error -> {
                ErrorViewHolder(
                    ParkSduiLoadStateErrorBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), retryMoreSection
                )
            }
            else -> {
                object: ViewHolder(View(parent.context)) {}
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ErrorViewHolder -> holder.bind()
        }
    }

    internal class LoadingViewHolder(
        binding: ParkSduiLoadStateLoadingBinding
    ): ViewHolder(binding.root)

    internal class ErrorViewHolder(
        private val binding: ParkSduiLoadStateErrorBinding,
        private val retryMoreSection: () -> Unit
    ): ViewHolder(binding.root) {

        fun bind() {
            binding.btnRetry.setOnClickListener {
                retryMoreSection.invoke()
            }
        }
    }
}