package com.example.mycontacts.view.main

import com.example.mycontacts.model.ContactData

interface ContactClickListener {
    fun onClick(contactData: ContactData)
}