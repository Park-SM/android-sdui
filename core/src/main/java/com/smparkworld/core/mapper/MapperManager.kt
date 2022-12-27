package com.smparkworld.core.mapper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperManager @Inject constructor(
    val dispatcher: MapperDispatcher
) {

    inline fun <reified FROM, reified TO> map(from: FROM): TO {
        // Here can use the Something Decorator to decorate the TO class.
        return dispatcher.map(from)
    }

    inline fun <reified FROM, reified TO> mapList(from: List<FROM>): List<TO> {
        // Here can use the Something Decorator to decorate the TO class.
        return from.map { dispatcher.map(it) }
    }
}