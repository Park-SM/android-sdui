package com.smparkworld.park.di

import com.smparkworld.park.di.annotation.SectionViewBinderKey
import com.smparkworld.park.di.annotation.SectionViewBinders
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.ui.model.SectionViewBinder
import com.smparkworld.park.ui.model.SectionViewType
import com.smparkworld.park.ui.model.viewbinder.ProductViewBinder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewBinderModule {

    @Binds
    @IntoMap
    @SectionViewBinders
    @SectionViewBinderKey(viewType = SectionViewType.PRODUCT_DEFAULT)
    abstract fun bindProductViewBinder(viewBinder: ProductViewBinder): SectionViewBinder<out SectionDTO, *>
}