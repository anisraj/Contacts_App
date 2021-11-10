package com.example.mycontacts.di.main_act

import com.example.mycontacts.view.main.MainActivity
import dagger.Subcomponent

@MainActScope
@Subcomponent(modules = [MainActModule::class])
interface MainActSubComponent {
    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create() : MainActSubComponent
    }
}