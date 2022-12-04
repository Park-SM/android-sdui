package com.smparkworld.core.mapper

import kotlin.reflect.KClass

interface Mapper<FROM, TO> {

    fun map(from: FROM): TO

    fun equals(from: KClass<*>, to: KClass<*>): Boolean
}