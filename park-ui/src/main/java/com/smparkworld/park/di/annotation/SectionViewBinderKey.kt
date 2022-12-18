package com.smparkworld.park.di.annotation

import dagger.MapKey

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class SectionViewBinderKey(val viewType: String)