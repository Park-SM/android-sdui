package com.smparkworld.data.di

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.data.mapper.BannerSectionDTOMapper
import com.smparkworld.data.mapper.ParkRefreshDTOMapper
import com.smparkworld.data.mapper.ParkRequestDTOMapper
import com.smparkworld.data.mapper.ParkSectionsDTOMapper
import com.smparkworld.data.mapper.ProductDetailDTOMapper
import com.smparkworld.data.mapper.ProductSectionDTOMapper
import com.smparkworld.data.mapper.ProductSectionVOMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    @IntoSet
    fun bindParkRequestDTOMapper(mapper: ParkRequestDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    fun bindParkSectionsDTOMapper(mapper: ParkSectionsDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    fun bindParkRefreshDTOMapper(mapper: ParkRefreshDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    fun bindProductSectionDTOMapper(mapper: ProductSectionDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    fun bindProductSectionVOMapper(mapper: ProductSectionVOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    fun bindBannerSectionDTOMapper(mapper: BannerSectionDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    fun bindProductDetailDTOMapper(mapper: ProductDetailDTOMapper): Mapper<*, *>
}