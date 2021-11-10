package com.example.mycontacts.di

import com.example.mycontacts.di.details_act.DetailsActSubComponent
import com.example.mycontacts.di.main_act.MainActSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun mainActSubComponent() : MainActSubComponent.Factory
    fun detailsActSubComponent() : DetailsActSubComponent.Factory
}