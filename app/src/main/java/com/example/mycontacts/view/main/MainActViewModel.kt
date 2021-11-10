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
    var noResultsVisible = ObservableField<Int>(View.GONE)


    private var columns = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.Data.CONTACT_ID
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
     *  @param list list of contacts
     *  @return ArrayList<ContactData> list of contacts after adding sort character
     */
    private fun addAlphabets(list: ArrayList<ContactData>): ArrayList<ContactData> {
        val customList: ArrayList<ContactData> = ArrayList<ContactData>()
        if (list.isNotEmpty()) {
            noResultsVisible.set(View.GONE)
            val firstMember = ContactData(list[0].name, list[0].mobileNumber, list[0].id, ContactType.MEMBER, "A")
            firstMember.name = list[0].name
            firstMember.char = list[0].name.first().toString()
            firstMember.type = ContactType.HEADER
            customList.add(firstMember)
            var i = 0
            while (i < list.size - 1) {
                val teamMember = ContactData(list[i].name, list[i].mobileNumber, list[i].id, ContactType.MEMBER, "A")
                val charOne = list[i].name.first()
                val charTwo: Char = list[i + 1].name.first()
                if (charOne == charTwo) {
                    list[i].type = ContactType.MEMBER
                    customList.add(list[i])
                } else {
                    list[i].type = ContactType.MEMBER
                    customList.add(list[i])
                    teamMember.type = ContactType.HEADER
                    teamMember.char = charTwo.toString()
                    customList.add(teamMember)
                }
                i++
            }
            list[i].type = ContactType.MEMBER
            customList.add(list[i])
        } else {
            noResultsVisible.set(View.VISIBLE)
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