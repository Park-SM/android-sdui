package com.smparkworld.main.ui.productlist

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.core.extension.viewModels
import com.smparkworld.main.R
import com.smparkworld.main.databinding.FragmentProductListBinding
import com.smparkworld.parkui.ui.ParkFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : ParkFragment<FragmentProductListBinding, ProductListViewModel>() {

    override val layout: Int = R.layout.fragment_product_list

    override val vm: ProductListViewModel by viewModels()

    override fun getSections(binding: FragmentProductListBinding): RecyclerView = binding.sections

    override fun onCreateBinding(binding: FragmentProductListBinding) {

        initViews(binding)
        initObservers(binding)
    }

    private fun initViews(binding: FragmentProductListBinding) {
        // do nothing
    }

    private fun initObservers(binding: FragmentProductListBinding) {
        vm.isEmpty.observe(viewLifecycleOwner) { isEmpty ->
            binding.layoutEmpty.isVisible = isEmpty
        }
        vm.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
    }
}