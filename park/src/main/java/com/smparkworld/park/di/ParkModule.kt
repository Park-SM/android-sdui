package com.smparkworld.park.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smparkworld.network.RuntimeTypeAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ParkModule {

    @Provides
    @Singleton
    fun provideGson(
        adapters: @JvmSuppressWildcards Set<RuntimeTypeAdapterFactory<*>>
    ): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .apply {
                adapters.forEach {
                    registerTypeAdapterFactory(it)
                }
            }
            .create()
    }
}