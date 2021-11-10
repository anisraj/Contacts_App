package com.example.mycontacts.model

import java.io.Serializable

data class ContactData(
    var name: String,
    var mobileNumber: String,
    var id: String,
    var type: ContactType,
    var char: String
) : Serializable
