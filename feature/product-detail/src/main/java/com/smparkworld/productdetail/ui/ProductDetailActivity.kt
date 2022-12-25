package com.smparkworld.productdetail.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smparkworld.productdetail.R
import com.smparkworld.productdetail.databinding.ActivityProductDetailBinding
import com.smparkworld.productdetail.ui.model.ProductDetailEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    private val vm by viewModels<ProductDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        binding.lifecycleOwner = this
        binding.vm = vm

        initViews()
        initObservers()
    }

    private fun initViews() {
        // do nothing.
    }

    private fun initObservers() {
        vm.event.observe(this) { event ->
            when (event) {
                is ProductDetailEvent.InvalidArgument -> {
                    Toast.makeText(this, "올바르지 않은 Product ID 입니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}