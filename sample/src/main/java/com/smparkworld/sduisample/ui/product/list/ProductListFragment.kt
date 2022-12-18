package com.smparkworld.sduisample.ui.product.list

import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.park.ui.park.ParkFragment
import com.smparkworld.sduisample.R
import com.smparkworld.sduisample.databinding.FragmentProductListBinding
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