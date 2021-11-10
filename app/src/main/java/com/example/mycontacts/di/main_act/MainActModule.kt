package com.example.mycontacts.di.main_act

import android.app.Application
import com.example.mycontacts.di.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActModule {

    @Provides
    @MainActScope
    fun providesMainViewModelFactory(
        application: Application
    ) : MainViewModelFactory {
        return MainViewModelFactory(application)
    }
}