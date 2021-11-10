package com.example.mycontacts.di.details_act

import com.example.mycontacts.di.DetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailsActModule {

    @Provides
    @DetailsActScope
    fun providesDetailsViewModelFactory() : DetailsViewModelFactory {
        return DetailsViewModelFactory()
    }
}