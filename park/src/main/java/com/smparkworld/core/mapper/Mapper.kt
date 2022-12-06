package com.smparkworld.core.mapper

import javax.inject.Inject
import kotlin.reflect.KClass

abstract class Mapper<FROM, TO> {

    @Inject
    lateinit var mapperManager: MapperManager

    abstract fun map(from: FROM): TO

    abstract fun equals(from: KClass<*>, to: KClass<*>): Boolean
}