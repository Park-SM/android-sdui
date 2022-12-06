package com.smparkworld.park.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.viewbinding.BuildConfig
import com.smparkworld.core.ExtraKey
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.usecase.GetSectionsUseCase
import com.smparkworld.park.model.Result
import com.smparkworld.park.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ParkViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getSectionsUseCase: GetSectionsUseCase
) : BaseViewModel(),
    EventListener {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _items: MutableLiveData<List<SectionDTO>> = MutableLiveData()
    val items: LiveData<List<SectionDTO>> get() = _items

    val isEmpty: LiveData<Boolean> get() = Transformations.map(_items) {
        it.isNullOrEmpty()
    }

    private var nextRequestUrl: String? = null

    init {
        requestSections()
    }

    private fun requestSections() {

        viewModelScope.launch {
            _isLoading.value = true

            val requestUrl = getRequestUrl()
            if (requestUrl != null) {

                when (val result = getSectionsUseCase(requestUrl)) {
                    is Result.Success -> {
                        onSuccessGetSections(result.data)
                    }
                    is Result.Error -> {
                        onFailureGetSections(result.exception)
                    }
                }
            } else {
                onEmptyRequestUrl()
            }

            _isLoading.value = false
        }
    }

    private fun onSuccessGetSections(data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _items.value = data.sections
    }

    private fun onFailureGetSections(exception: Exception) {
        // Send non-fatal log, etc..

        if (BuildConfig.DEBUG) exception.printStackTrace()
    }

    private fun onEmptyRequestUrl() {
        // Send non-fatal log, etc..

        _items.value = emptyList()
    }

    private fun getRequestUrl() = stateHandle.get<String>(ExtraKey.REQUEST_URL)
}