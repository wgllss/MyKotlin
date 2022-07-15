package com.atar.annotations

import kotlin.reflect.KClass
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class APTMoudle(val clazz: KClass<out Any>)
