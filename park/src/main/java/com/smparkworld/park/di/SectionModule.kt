package com.smparkworld.park.di

import com.smparkworld.network.RuntimeTypeAdapterFactory
import com.smparkworld.park.data.vo.ProductSectionVO
import com.smparkworld.park.data.vo.SectionVO
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
        }
    }
}