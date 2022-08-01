package com.example.myapplication.ui.main.hint

import android.content.Context
import com.scclzkj.api.Api
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface InitializerEntryPoint {

    fun injectAPi(): Api

    companion object {
        fun resolve(context: Context): InitializerEntryPoint {
            val appContext = context.applicationContext ?: throw IllegalStateException()
            return EntryPointAccessors.fromApplication(appContext, InitializerEntryPoint::class.java)
        }
    }
}