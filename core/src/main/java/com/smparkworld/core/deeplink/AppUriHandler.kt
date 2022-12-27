package com.smparkworld.core.deeplink

import android.app.Activity
import android.net.Uri
import com.smparkworld.core.BuildConfig
import com.smparkworld.core.extension.hostAndPath
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AppUriHandler @Inject constructor(
    private val handlers: @JvmSuppressWildcards Map<String, Provider<UriHandler>>
) : UriHandler {

    override fun handle(activity: Activity, uri: Uri?): Boolean {
        if (uri == null) return false

        return try {
            handlers[uri.hostAndPath]?.get()?.handle(activity, uri)
                ?: throw IllegalArgumentException("An unknown deep link has been detected, requested uri is $uri.")
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            // Send non-fatal log, etc..
            false
        }
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class UriParserKey(val host: String)