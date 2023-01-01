package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface RedirectDelegator {

    val _redirectUriByRedirectDelegator: MutableLiveData<Uri>

    fun redirectToUri(linkUri: String)

    fun onRedirectToUri(linkUri: String) {}
}