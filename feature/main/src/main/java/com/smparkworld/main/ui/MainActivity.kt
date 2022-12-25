package com.smparkworld.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smparkworld.main.R
import com.smparkworld.main.databinding.ActivityMainBinding
import com.smparkworld.main.ui.productlist.ProductListFragment
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

        val uri = "/products"

        val fragment = ProductListFragment().setRequestUri(uri)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

    private fun initObservers() {
        // do nothing
    }
}