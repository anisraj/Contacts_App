package com.example.mycontacts

import android.app.Application
import com.example.mycontacts.di.AppComponent
import com.example.mycontacts.di.AppModule
import com.example.mycontacts.di.DaggerAppComponent
import com.example.mycontacts.di.Injector
import com.example.mycontacts.di.details_act.DetailsActSubComponent
import com.example.mycontacts.di.main_act.MainActSubComponent

class MyApplication : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun createMainActSubComponent(): MainActSubComponent {
        return appComponent.mainActSubComponent().create()
    }

    override fun createDetailsActSubComponent(): DetailsActSubComponent {
        return appComponent.detailsActSubComponent().create()
    }
}