package com.smparkworld.product_list

import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.park_ui.park.ParkFragment
import com.smparkworld.product_list.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : ParkFragment<FragmentProductListBinding>() {

    override val layout: Int = R.layout.fragment_product_list

    override fun getSections(binding: FragmentProductListBinding): RecyclerView = binding.sections

    override fun onCreateBinding(binding: FragmentProductListBinding) {

        initViews(binding)
        initObservers(binding)
    }

    private fun initViews(binding: FragmentProductListBinding) {

    }

    private fun initObservers(binding: FragmentProductListBinding) {

    }
}