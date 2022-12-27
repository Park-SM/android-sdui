package com.smparkworld.data.di

import com.smparkworld.data.vo.BannerSectionVO
import com.smparkworld.data.vo.ProductSectionVO
import com.smparkworld.data.vo.SectionVO
import com.smparkworld.network.RuntimeTypeAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class SectionModule {

    @Provides
    @IntoSet
    fun bindSectionFactory(): RuntimeTypeAdapterFactory<*> {
        return RuntimeTypeAdapterFactory.of(SectionVO::class.java, "sectionType").apply {
            //registerDefaultSubtype(NoneSectionVO::class.java, "NONE")
            registerSubtype(ProductSectionVO::class.java, "PRODUCT_SECTION")
            registerSubtype(BannerSectionVO::class.java, "BANNER_SECTION")
        }
    }
}