package com.smparkworld.sduisample

import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.park.ui.ParkFragment
import com.smparkworld.sduisample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ParkFragment<FragmentHomeBinding>() {

    override val layout: Int = R.layout.fragment_home

    override fun getSections(binding: FragmentHomeBinding): RecyclerView = binding.sections

    override fun onCreateBinding(binding: FragmentHomeBinding) {

        initViews(binding)
        initObservers(binding)
    }

    private fun initViews(binding: FragmentHomeBinding) {

    }

    private fun initObservers(binding: FragmentHomeBinding) {

    }
}