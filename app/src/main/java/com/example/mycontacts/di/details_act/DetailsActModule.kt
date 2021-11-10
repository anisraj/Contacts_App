package com.example.mycontacts.di.details_act

import android.app.Application
import com.example.mycontacts.di.DetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailsActModule {

    @Provides
    @DetailsActScope
    fun providesDetailsViewModelFactory(
        application: Application
    ) : DetailsViewModelFactory {
        return DetailsViewModelFactory(application)
    }
}