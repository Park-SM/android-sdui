package com.smparkworld.park.di

import com.smparkworld.park.di.qualifier.SectionViewBinderKey
import com.smparkworld.park.di.qualifier.SectionViewBinders
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.model.sections.ProductSectionDTO
import com.smparkworld.park.ui.model.SectionViewBinder
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
    @SectionViewBinderKey(ProductSectionDTO::class)
    abstract fun bindProductViewBinder(viewBinder: ProductViewBinder): SectionViewBinder<out SectionDTO, *>
}