package com.example.myapplication.ui.main.dagger2

import com.example.myapplication.ui.main.viewmodel.CustomItemDataSource
import dagger.Component

@Component()
interface MainComponent {

    fun inject(customitemdatasource: CustomItemDataSource)

}