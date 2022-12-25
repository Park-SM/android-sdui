package com.smparkworld.productdetail.di

import com.smparkworld.core.DeeplinkHost
import com.smparkworld.core.deeplink.UriHandler
import com.smparkworld.core.deeplink.UriParserKey
import com.smparkworld.productdetail.deeplink.ProductDetailUriHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface UriHandlerModule {

    @Binds
    @IntoMap
    @UriParserKey(DeeplinkHost.PRODUCT_DETAIL)
    fun bindProductDetailUriHandler(handler: ProductDetailUriHandler): UriHandler
}