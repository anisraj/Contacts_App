package com.example.mycontacts.view.contact_detail

import android.app.Application
import android.content.ContentUris
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import android.content.ContentResolver
import java.lang.Exception

class ContactDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var uiActionLiveData = MutableLiveData<ContactDetailsUiAction>()
    private val context = getApplication<Application>().applicationContext

    /** This fun used for getting contact photo
     *  @param contentResolver content resolver
     *  @param contactId id of the contact
     */
    fun getPhotoUri(contentResolver: ContentResolver, contactId: Long): Uri? {
        try {
            val cursor = contentResolver.query(
                    ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID
                            + "="
                            + contactId
                            + " AND "
                            + ContactsContract.Data.MIMETYPE
                            + "='"
                            + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE
                            + "'", null, null
                )
            if (cursor != null) {
                if (!cursor.moveToFirst()) {
                    return null // no photo
                }
            } else {
                return null // error in cursor process
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val person = ContentUris.withAppendedId(
            ContactsContract.Contacts.CONTENT_URI, contactId
        )
        return Uri.withAppendedPath(
            person,
            ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
        )
    }

    /** This fun return onclick actions for observing in view
     *  @return MutableLiveData<ContactDetailsUiAction>
     */
    fun getOnClickActions() : MutableLiveData<ContactDetailsUiAction> {
        return uiActionLiveData
    }

    /** below functions are for ui actions
     *
     */
    fun closeButtonClick() {
        uiActionLiveData.postValue(ContactDetailsUiAction.CLOSE_BUTTON)
    }
    fun callButtonClick() {
        uiActionLiveData.postValue(ContactDetailsUiAction.CALL_BUTTON)
    }
    fun messageButtonClick() {
        uiActionLiveData.postValue(ContactDetailsUiAction.MESSAGE_BUTTON)
    }

}