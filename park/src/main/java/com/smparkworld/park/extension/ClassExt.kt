package com.smparkworld.park.extension

import kotlin.reflect.KClass

fun KClass<*>.isAssignableFrom(from: KClass<*>): Boolean =
    this.java.isAssignableFrom(from.java)