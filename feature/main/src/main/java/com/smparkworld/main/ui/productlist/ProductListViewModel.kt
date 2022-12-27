package com.smparkworld.main.ui.productlist

import androidx.lifecycle.SavedStateHandle
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.dto.SectionDTO
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
        // hook function
    }

    override fun onSuccessMoreRequest(data: ParkSectionsDTO) {
        // hook function
    }

    override fun onSuccessPartialUpdate(items: List<SectionDTO>) {
        // hook function
    }

    override fun onFailureRequest(exception: Exception) {
        // hook function
    }

    override fun onEmptySections() {
        // hook function
    }

    override fun onRequestWishState(origin: List<SectionDTO>, id: Long, isWished: Boolean) {
        // hook function
    }

    override fun onRedirectToUri(linkUri: String) {
        // hook function
    }
}