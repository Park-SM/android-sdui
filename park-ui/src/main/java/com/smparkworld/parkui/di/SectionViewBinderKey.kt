package com.smparkworld.parkui.di

import dagger.MapKey

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class SectionViewBinderKey(val viewType: String)