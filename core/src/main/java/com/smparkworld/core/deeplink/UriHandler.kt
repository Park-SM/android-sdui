package com.smparkworld.core.deeplink

import android.app.Activity
import android.net.Uri

interface UriHandler {

    fun handle(activity: Activity, uri: Uri?): Boolean
}