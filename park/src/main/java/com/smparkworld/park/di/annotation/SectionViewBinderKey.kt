package com.smparkworld.park.di.annotation

import com.smparkworld.park.domain.dto.SectionDTO
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class SectionViewBinderKey(val model: KClass<out SectionDTO>)