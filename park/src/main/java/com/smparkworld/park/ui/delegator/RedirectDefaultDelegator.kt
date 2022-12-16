package com.smparkworld.park.ui.delegator

import android.util.Log
import javax.inject.Inject

class RedirectDefaultDelegator @Inject constructor(

) : RedirectDelegator {

    override fun redirectToUrl(linkUrl: String) {
        Log.d("Test!!", "Clicked item! | linkUrl: $linkUrl")
    }
}