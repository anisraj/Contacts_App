package com.example.mycontacts.di.details_act

import com.example.mycontacts.view.contact_detail.ContactsDetailActivity
import dagger.Subcomponent

@DetailsActScope
@Subcomponent(modules = [DetailsActModule::class])
interface DetailsActSubComponent {
    fun inject(detailsAct: ContactsDetailActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create() : DetailsActSubComponent
    }
}