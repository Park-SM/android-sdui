package com.smparkworld.sduisample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smparkworld.sduisample.R
import com.smparkworld.sduisample.databinding.ActivityMainBinding
import com.smparkworld.sduisample.ui.product.list.ProductListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViews()
        initObservers()
    }

    private fun initViews() {

        val url = "/products"

        val fragment = ProductListFragment().setRequestUrl(url)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

    private fun initObservers() {
        // do nothing
    }
}