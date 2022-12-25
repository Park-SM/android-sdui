package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import androidx.lifecycle.LiveData
import com.smparkworld.core.SingleLiveEvent
import javax.inject.Inject

class RedirectDefaultDelegator @Inject constructor(

) : RedirectDelegator {

    private val _redirectUri = SingleLiveEvent<Uri>()
    override val redirectUri: LiveData<Uri> get() = _redirectUri

    override fun redirectToUri(linkUri: String) {
        _redirectUri.value = Uri.parse(linkUri)
    }
}