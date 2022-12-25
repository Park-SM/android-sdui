package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface RedirectDelegator {

    val _redirectUri: MutableLiveData<Uri>

    fun redirectToUri(linkUri: String)
}