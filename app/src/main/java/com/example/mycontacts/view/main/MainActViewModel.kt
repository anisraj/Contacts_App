package com.example.mycontacts.view.main

import android.app.Application
import android.provider.ContactsContract
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mycontacts.model.ContactData
import com.example.mycontacts.model.ContactType

class MainActViewModel(application: Application) : AndroidViewModel(application) {

    private val contactsData = MutableLiveData<ArrayList<ContactData>>()
    private val animate = MutableLiveData<String>()
    private val context = getApplication<Application>().applicationContext
    var progressVisible = ObservableField<Int>(View.GONE)

    private var columns = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID,
    ).toTypedArray()

    /** This function is using for reading contacts
     *  @param searchQuery when user search contact then this param is used
     */
    fun readContacts(searchQuery: String) {
        progressVisible.set(View.VISIBLE)
        val result = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            columns,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE ?",
            Array(1){"%$searchQuery%"},
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        var contactsList = ArrayList<ContactData>()
        while (result?.moveToNext()!!) {
            contactsList.add(
                ContactData(result.getString(0), result.getString(1), result.getString(2), ContactType.MEMBER, "A")
            )
        }
        progressVisible.set(View.GONE)
        contactsData.postValue(addAlphabets(contactsList))
    }

    /** This fun using for adding sort character
     *
     *  @param contacts list of contacts
     *  @return ArrayList<ContactData> list of contacts after adding sort character
     */
    private fun addAlphabets(contacts: ArrayList<ContactData>) : ArrayList<ContactData> {
        var i = 0
        var customList = ArrayList<ContactData>()
        for (contactPosition in i..(contacts.size-2)) {
            var member = ContactData(contacts[contactPosition].name, contacts[contactPosition].mobileNumber, contacts[contactPosition].id, contacts[contactPosition].type, contacts[contactPosition].name.first().toString())
            if (contacts[contactPosition].name.first().toString() == contacts[contactPosition + 1].name.first().toString()) {
                member.char = contacts[contactPosition].name.first().toString()
                member.type = ContactType.MEMBER
                customList.add(member)
            } else {
                member.char = contacts[contactPosition + 1].name.first().toString()
                member.type = ContactType.HEADER
                customList.add(member)
            }
        }
        return customList
    }

    /** This function is using returning contactsData for observing in view
     *  @return MutableLiveData which return contactsData
     */
    fun getContactsDataObject() : MutableLiveData<ArrayList<ContactData>> {
        return contactsData
    }

    fun getAnimate() : MutableLiveData<String> {
        return animate
    }

    /**
     * fun for handling refresh click
     */
    fun refreshData(v: View) {
        animate.postValue("")
        readContacts("")
    }
}