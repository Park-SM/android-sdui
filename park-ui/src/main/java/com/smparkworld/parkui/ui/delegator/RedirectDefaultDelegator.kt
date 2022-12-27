package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import com.smparkworld.core.SingleLiveEvent
import javax.inject.Inject

class RedirectDefaultDelegator @Inject constructor(

) : RedirectDelegator {

    override val _redirectUri = SingleLiveEvent<Uri>()

    override fun redirectToUri(linkUri: String) {
        onRedirectToUri(linkUri)
        _redirectUri.value = Uri.parse(linkUri)
    }
}