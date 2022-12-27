package com.smparkworld.productdetail.deeplink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.smparkworld.core.DeeplinkHost
import com.smparkworld.core.ExtraKey
import com.smparkworld.core.QueryParamKey
import com.smparkworld.core.deeplink.UriHandler
import com.smparkworld.core.extension.hostAndPath
import com.smparkworld.productdetail.ui.ProductDetailActivity
import javax.inject.Inject

class ProductDetailUriHandler @Inject constructor() : UriHandler {

    override fun handle(activity: Activity, uri: Uri?): Boolean {
        if (uri == null) return false

        when {
            isProductDetail(uri) -> {

                val productId = uri.getQueryParameter(QueryParamKey.PRODUCT_ID)?.toLongOrNull()
                    ?: return false

                val intent = Intent(activity, ProductDetailActivity::class.java).apply {
                    putExtra(ExtraKey.PRODUCT_ID, productId)
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

    // parkui://product/detail?product_id={id}
    private fun isProductDetail(uri: Uri): Boolean =
        uri.hostAndPath == DeeplinkHost.PRODUCT_DETAIL
                && uri.getQueryParameter(QueryParamKey.PRODUCT_ID)?.toLongOrNull() != null
}