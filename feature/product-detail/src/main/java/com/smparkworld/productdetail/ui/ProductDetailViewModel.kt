package com.smparkworld.productdetail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smparkworld.core.ExtraKey
import com.smparkworld.core.SingleLiveEvent
import com.smparkworld.domain.Result
import com.smparkworld.productdetail.BuildConfig
import com.smparkworld.productdetail.ui.delegator.ProductDelegator
import com.smparkworld.productdetail.ui.delegator.ProductFakeDelegator
import com.smparkworld.productdetail.ui.model.ProductDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    productFakeDelegator: ProductFakeDelegator
) : ViewModel(),
    ProductDelegator by productFakeDelegator {

    private val _imageUri = MutableLiveData<String>()
    val imageUri: LiveData<String> get() = _imageUri

    private val _isWished = MutableLiveData<Boolean>()
    val isWished: LiveData<Boolean> get() = _isWished

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _reviewScore = MutableLiveData<String>()
    val reviewScore: LiveData<String> get() = _reviewScore

    private val _price = MutableLiveData<String>()
    val price: LiveData<String> get() = _price

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> get() = _category

    private val _event = SingleLiveEvent<ProductDetailEvent>()
    val event: LiveData<ProductDetailEvent> get() = _event

    init {
        requestProduct()
    }

    private fun requestProduct() {

        viewModelScope.launch {

            val productId = getProductId()
            if (productId != null) {

                when (val result = getProductById(productId)) {
                    is Result.Success -> {
                        onSuccessGetProduct(result.data)
                    }
                    is Result.Error -> {
                        onFailureGetProduct(result.exception)
                    }
                }
            } else {
                _event.value = ProductDetailEvent.InvalidArgument
            }
        }
    }

    private fun onSuccessGetProduct(model: com.smparkworld.domain.dto.tmp.ProductDTO) {
        _imageUri.value = model.imageUri ?: ""
        _isWished.value = model.isWished ?: false
        _title.value = model.title ?: ""
        _reviewScore.value = model.reviewScore ?: ""
        _price.value = model.price ?: ""
        _category.value = model.category ?: ""
    }

    private fun onFailureGetProduct(exception: Exception) {
        if (BuildConfig.DEBUG) exception.printStackTrace()

        _event.value = ProductDetailEvent.InvalidArgument
    }

    private fun getProductId(): Long? = savedStateHandle[ExtraKey.PRODUCT_ID]
}