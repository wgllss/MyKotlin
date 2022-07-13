package com.atar.annotations

import kotlin.reflect.KClass


@Suppress("SupportAnnotationUsage")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class CreateService(val interfaceApi: KClass<out Any>, val superClass: String)


