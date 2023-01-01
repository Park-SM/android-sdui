package com.smparkworld.core.ui.support.recyclerview

sealed class BottomLoadState {

    object IsNotLoading : BottomLoadState()

    object IsLoading : BottomLoadState()

    data class Error(
        val exception: Exception
    ) : BottomLoadState()
}