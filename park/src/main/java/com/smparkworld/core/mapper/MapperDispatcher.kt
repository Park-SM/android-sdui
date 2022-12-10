package com.smparkworld.core.mapper

import javax.inject.Inject
import kotlin.reflect.KClass

private typealias MappersType = Mapper<*, *>

class MapperDispatcher @Inject constructor(
    val mappers: @JvmSuppressWildcards Set<MappersType>
) {

    @Suppress("UNCHECKED_CAST")
    inline fun <reified FROM, reified TO> getMapper(): Mapper<FROM, TO> {

        val matchedMappers = mappers.filter { it.equals(FROM::class, TO::class) }
        if (matchedMappers.size == 1) {
            return matchedMappers.getOrNull(0) as? Mapper<FROM, TO>
                ?: throw IllegalArgumentException(getNotFoundErrorMessage(FROM::class, TO::class))
        }
        if (matchedMappers.isEmpty()) {
            throw IllegalArgumentException(getNotFoundErrorMessage(FROM::class, TO::class))
        }

        throw IllegalArgumentException(getNonUniqueMapperErrorMessage(FROM::class, TO::class))
    }

    fun getNotFoundErrorMessage(from: KClass<*>, to: KClass<*>): String =
        "Not found mapper class. | from: ${from.simpleName}, to: ${to.simpleName}"

    fun getNonUniqueMapperErrorMessage(from: KClass<*>, to: KClass<*>): String =
        "Not unique mapper class. | from: ${from.simpleName}, to: ${to.simpleName}"
}