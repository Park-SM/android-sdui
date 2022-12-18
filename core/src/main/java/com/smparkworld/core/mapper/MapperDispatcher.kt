package com.smparkworld.core.mapper

import javax.inject.Inject
import kotlin.reflect.KClass

private typealias MappersType = Mapper<*, *>

class MapperDispatcher @Inject constructor(
    val mappers: @JvmSuppressWildcards Set<MappersType>
) {

    @Suppress("UNCHECKED_CAST")
    inline fun <reified FROM, reified TO> map(from: FROM): TO {

        val matchedMappers = mappers.filter { it.equals(FROM::class, TO::class) }
        if (matchedMappers.isEmpty()) {
            throw IllegalArgumentException(getNotFoundErrorMessage(FROM::class, TO::class))
        }

        for (mapper in matchedMappers) {
            try {
                return (mapper as Mapper<FROM, TO>).map(from)
            } catch (e: ClassCastException) {
                continue
            }
        }

        throw IllegalArgumentException(getNotFoundErrorMessage(FROM::class, TO::class))
    }

    fun getNotFoundErrorMessage(from: KClass<*>, to: KClass<*>): String =
        "Not found mapper class. | from: ${from.simpleName}, to: ${to.simpleName}"
}