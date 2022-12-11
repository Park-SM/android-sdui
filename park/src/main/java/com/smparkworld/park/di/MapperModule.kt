package com.smparkworld.park.di

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.park.data.mapper.ParkRequestDTOMapper
import com.smparkworld.park.data.mapper.ParkSectionsDTOMapper
import com.smparkworld.park.data.mapper.section.BannerSectionDTOMapper
import com.smparkworld.park.data.mapper.section.ProductSectionDTOMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @IntoSet
    abstract fun bindParkRequestDTOMapper(mapper: ParkRequestDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    abstract fun bindParkSectionsDTOMapper(mapper: ParkSectionsDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    abstract fun bindProductSectionDTOMapper(mapper: ProductSectionDTOMapper): Mapper<*, *>

    @Binds
    @IntoSet
    abstract fun bindBannerSectionDTOMapper(mapper: BannerSectionDTOMapper): Mapper<*, *>
}