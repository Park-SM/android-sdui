package com.smparkworld.data.di

import com.smparkworld.data.source.remote.SectionRemoteDataSource
import com.smparkworld.data.source.remote.SectionRemoteDataSourceFakeImpl
import com.smparkworld.data.source.remote.WishRemoteDataSource
import com.smparkworld.data.source.remote.WishRemoteDataSourceFakeImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindSectionDataSourceFakeImpl(source: SectionRemoteDataSourceFakeImpl): SectionRemoteDataSource

    @Binds
    fun bindWishRemoteDataSourceFakeImpl(source: WishRemoteDataSourceFakeImpl): WishRemoteDataSource
}