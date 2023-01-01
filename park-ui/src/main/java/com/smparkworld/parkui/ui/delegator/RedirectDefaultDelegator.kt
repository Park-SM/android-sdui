package com.smparkworld.parkui.ui.delegator

import android.net.Uri
import com.smparkworld.core.SingleLiveEvent
import javax.inject.Inject

class RedirectDefaultDelegator @Inject constructor(

) : RedirectDelegator {

    override val _delegatedRedirectUriByRedirectDelegator = SingleLiveEvent<Uri>()

    override fun redirectToUri(linkUri: String) {
        onRedirectToUri(linkUri)
        _delegatedRedirectUriByRedirectDelegator.value = Uri.parse(linkUri)
    }
}