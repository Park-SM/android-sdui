package com.smparkworld.main.deeplink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.smparkworld.core.DeeplinkHost
import com.smparkworld.core.deeplink.UriHandler
import com.smparkworld.core.extension.hostAndPath
import com.smparkworld.main.ui.MainActivity
import javax.inject.Inject

class MainUriHandler @Inject constructor() : UriHandler {

    override fun handle(activity: Activity, uri: Uri?): Boolean {
        if (uri == null) return false

        when {
            isMainUri(uri) -> {

                val intent = Intent(activity, MainActivity::class.java).apply {
                    // If use the Bottom Navigation UI, save index to show fragment via putExtra function.
                }

                activity.startActivity(intent)
            }
            else -> {
                // Send non-fatal log, etc..
                return false
            }
        }
        return true
    }

    // parkui://main
    private fun isMainUri(uri: Uri): Boolean =
        uri.hostAndPath == DeeplinkHost.MAIN
}