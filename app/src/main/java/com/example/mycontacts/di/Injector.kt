package com.example.mycontacts.di

import com.example.mycontacts.di.details_act.DetailsActSubComponent
import com.example.mycontacts.di.main_act.MainActSubComponent

interface Injector {
    fun createMainActSubComponent() : MainActSubComponent
    fun createDetailsActSubComponent() : DetailsActSubComponent
}