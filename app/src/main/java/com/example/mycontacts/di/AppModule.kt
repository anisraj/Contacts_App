package com.example.mycontacts.di

import android.app.Application
import com.example.mycontacts.di.details_act.DetailsActSubComponent
import com.example.mycontacts.di.main_act.MainActSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MainActSubComponent::class, DetailsActSubComponent::class])
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() : Application {
        return application
    }
}