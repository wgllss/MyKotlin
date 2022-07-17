package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.BuildConfig
import com.example.myapplication.net.Config
import com.example.myapplication.net.RetrofitClient
//import com.example.myapplication.ui.main.dagger2.AppComponent
//import com.example.myapplication.ui.main.dagger2.ApplicationModule
//import com.example.myapplication.ui.main.dagger2.DaggerAppComponent
import com.example.myapplication.uitls.AppBuildConfig
import dagger.hilt.android.HiltAndroidApp
import okhttp3.logging.HttpLoggingInterceptor
@HiltAndroidApp
class LCApplication : Application() {

//    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//        DaggerAppComponent.builder()
//            .applicationModule(ApplicationModule(this))
//            .build()
//    }

    companion object {
        lateinit var application: LCApplication;
    }

    override fun onCreate() {
        super.onCreate()
        application = this
//        appComponent.inJectAppContext(this)
        AppBuildConfig.APPLICATION = LCApplication::class.java.getName()
    }
}