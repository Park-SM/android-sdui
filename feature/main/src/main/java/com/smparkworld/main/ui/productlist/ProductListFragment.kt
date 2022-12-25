package com.smparkworld.main.ui.productlist

import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.main.R
import com.smparkworld.main.databinding.FragmentProductListBinding
import com.smparkworld.parkui.ui.ParkFragment
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