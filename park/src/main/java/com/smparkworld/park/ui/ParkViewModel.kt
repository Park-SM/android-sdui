package com.smparkworld.park.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import com.smparkworld.core.ExtraKey
import com.smparkworld.core.SingleLiveEvent
import com.smparkworld.park.BuildConfig
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.usecase.CreateWishUseCase
import com.smparkworld.park.domain.usecase.DeleteWishUseCase
import com.smparkworld.park.domain.usecase.GetSectionsUseCase
import com.smparkworld.park.domain.usecase.RollbackSectionWishStateUseCase
import com.smparkworld.park.model.Result
import com.smparkworld.park.ui.base.BaseViewModel
import com.smparkworld.park.ui.model.SectionItemEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ParkViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getSectionsUseCase: GetSectionsUseCase,
    private val createWishUseCase: CreateWishUseCase,
    private val deleteWishUseCase: DeleteWishUseCase,
    private val rollbackSectionWishStateUseCase: RollbackSectionWishStateUseCase
) : BaseViewModel(),
    EventListener {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _items = MutableLiveData<List<SectionDTO>>()
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

    override fun onClickItem(v: View, event: SectionItemEvent) {
        when (event) {
            is SectionItemEvent.Click -> {
                val linkUrl = event.model.getRedirectUrl()
                // Redirect to linkUrl
                Log.d("Test!!", "Clicked item! | linkUrl: $linkUrl")
            }
            is SectionItemEvent.LongClick -> {
                val linkUrl = event.model.getRedirectUrl()
                // Redirect to linkUrl
            }
            is SectionItemEvent.WishClick -> {
                val isWished = event.isWished
                val id = event.model.getWishTargetId() ?: return

                onClickWish(id, isWished)
            }
        }
    }

    private fun onSuccessGetSections(data: ParkSectionsDTO) {
        nextRequestUrl = data.requestUrl?.nextPageUrl

        _items.value = data.sections
    }

    private fun onFailureGetSections(exception: Exception) {
        // Send non-fatal log, etc..

    }

    private fun onEmptyRequestUrl() {
        // Send non-fatal log, etc..

        _items.value = emptyList()
    }

    private fun onClickWish(id: Long, isWished: Boolean) {

        viewModelScope.launch {

            val result = if (isWished) {
                createWishUseCase(id)
            } else {
                deleteWishUseCase(id)
            }
            when (result) {
                is Result.Success -> {
                    // do anything on wish api success. e.g) Show Snackbar.. etc..
                }
                is Result.Error -> {
                    // do rollback wish state
                    onRollbackWishState(id, isWished)

                    // do anything on wish api failure. e.g) Show Snackbar.. etc..
                }
            }
        }
    }

    private suspend fun onRollbackWishState(id: Long, isWished: Boolean) {

        val payload = RollbackSectionWishStateUseCase.Payload(
            id = id,
            isWished = isWished,
            originItems = _items.value ?: return
        )

        when (val result = rollbackSectionWishStateUseCase(payload)) {
            is Result.Success -> {
                _items.value = result.data
            }
            is Result.Error -> {
                // Send non-fatal log, etc..
            }
        }
    }

    private fun getRequestUrl() = stateHandle.get<String>(ExtraKey.REQUEST_URL)
}