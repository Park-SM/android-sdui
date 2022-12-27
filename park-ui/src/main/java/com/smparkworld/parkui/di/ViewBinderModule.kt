package com.smparkworld.parkui.di

import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.parkui.ui.model.SectionViewBinder
import com.smparkworld.parkui.ui.model.SectionViewTypeKey
import com.smparkworld.parkui.ui.model.viewbinder.BannerOneColumnViewBinder
import com.smparkworld.parkui.ui.model.viewbinder.ProductOneColumn2ViewBinder
import com.smparkworld.parkui.ui.model.viewbinder.ProductOneColumnViewBinder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface ViewBinderModule {

    @Binds
    @IntoMap
    @SectionViewBinders
    @SectionViewBinderKey(viewType = SectionViewTypeKey.PRODUCT_ONE_COLUMN)
    fun bindProductOneColumnViewBinder(viewBinder: ProductOneColumnViewBinder): SectionViewBinder<out SectionDTO, *>

    @Binds
    @IntoMap
    @SectionViewBinders
    @SectionViewBinderKey(viewType = SectionViewTypeKey.PRODUCT_ONE_COLUMN_2)
    fun bindProductOneColumn2ViewBinder(viewBinder: ProductOneColumn2ViewBinder): SectionViewBinder<out SectionDTO, *>

    @Binds
    @IntoMap
    @SectionViewBinders
    @SectionViewBinderKey(viewType = SectionViewTypeKey.BANNER_ONE_COLUMN)
    fun bindBannerOneColumnViewBinder(viewBinder: BannerOneColumnViewBinder): SectionViewBinder<out SectionDTO, *>
}