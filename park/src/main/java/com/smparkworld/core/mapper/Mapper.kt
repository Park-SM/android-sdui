package com.smparkworld.core.mapper

import dagger.Lazy
import javax.inject.Inject

abstract class Mapper<FROM, TO> {

    @Inject
    lateinit var mapperManagerLazy: Lazy<MapperManager>

    inline fun <reified T, reified R> delegateMap(from: T?): R? {
        return from?.let {
            mapperManagerLazy.get().map(it)
        }
    }

    inline fun <reified T, reified R> delegateMaps(from: List<T>?): List<R>? {
        return from?.map {
            mapperManagerLazy.get().map(it)
        }
    }

    abstract fun map(from: FROM): TO

    abstract fun equals(from: Class<*>, to: Class<*>): Boolean
}