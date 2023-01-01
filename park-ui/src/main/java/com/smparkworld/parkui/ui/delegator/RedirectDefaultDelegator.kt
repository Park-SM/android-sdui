package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import com.smparkworld.core.MutableLiveEvent
import javax.inject.Inject

class RedirectDefaultDelegator @Inject constructor(

) : RedirectDelegator {

    override val _redirectUriByRedirectDelegator = MutableLiveEvent<Uri>()

    override fun redirectToUri(linkUri: String) {
        onRedirectToUri(linkUri)
        _redirectUriByRedirectDelegator.value = Uri.parse(linkUri)
    }
}