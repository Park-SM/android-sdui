package com.smparkworld.core.mapper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperManager @Inject constructor(
    val dispatcher: MapperDispatcher
) {

    inline fun <reified FROM, reified TO> map(from: FROM): TO {
        // Here can use the Something Decorator to decorate the TO class. | e.g) Result<TO>
        return dispatcher.getMapper(from)
    }
}