package com.smparkworld.data.di

import com.smparkworld.data.repository.ProductRepositoryImpl
import com.smparkworld.data.repository.SectionRepositoryImpl
import com.smparkworld.data.repository.WishRepositoryImpl
import com.smparkworld.domain.repository.ProductRepository
import com.smparkworld.domain.repository.SectionRepository
import com.smparkworld.domain.repository.WishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindSectionRepository(repository: SectionRepositoryImpl): SectionRepository

    @Binds
    @Singleton
    fun bindWishRepository(repository: WishRepositoryImpl): WishRepository

    @Binds
    @Singleton
    fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository
}