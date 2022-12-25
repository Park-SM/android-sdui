package com.smparkworld.main.ui.productlist

import androidx.lifecycle.SavedStateHandle
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.parkui.ui.ParkViewModel
import com.smparkworld.parkui.ui.delegator.ParkDefaultDelegators
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    defaultDelegators: ParkDefaultDelegators
) : ParkViewModel(savedStateHandle, defaultDelegators) {

    override fun onSuccessRequest(data: ParkSectionsDTO) {
        // do nothing
    }

    override fun onSuccessMoreRequest(data: ParkSectionsDTO) {
        // do nothing
    }

    override fun onFailureRequest(exception: Exception) {
        // do nothing
    }
}