package com.smparkworld.data.di

import com.smparkworld.data.source.remote.FakeProductRemoteDataSourceImpl
import com.smparkworld.data.source.remote.FakeSectionRemoteDataSourceImpl
import com.smparkworld.data.source.remote.FakeWishRemoteDataSourceImpl
import com.smparkworld.data.source.remote.ProductRemoteDataSource
import com.smparkworld.data.source.remote.SectionRemoteDataSource
import com.smparkworld.data.source.remote.WishRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindSectionDataSourceImpl(source: FakeSectionRemoteDataSourceImpl): SectionRemoteDataSource

    @Binds
    fun bindWishRemoteDataSourceImpl(source: FakeWishRemoteDataSourceImpl): WishRemoteDataSource

    @Binds
    fun bindProductRemoteDataSourceImpl(source: FakeProductRemoteDataSourceImpl): ProductRemoteDataSource
}