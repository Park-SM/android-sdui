package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import androidx.lifecycle.LiveData

internal interface RedirectDelegator {

    val redirectUri: LiveData<Uri>

    fun redirectToUri(linkUri: String)
}