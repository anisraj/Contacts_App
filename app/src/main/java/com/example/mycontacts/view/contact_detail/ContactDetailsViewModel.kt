package com.example.mycontacts.view.contact_detail

import android.app.Application
import android.content.ContentUris
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mycontacts.R

class ContactDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var uiActionLiveData = MutableLiveData<ContactDetailsUiAction>()
    private val context = getApplication<Application>().applicationContext
    var photoLiveData = MutableLiveData<Bitmap>()

    /** This fun used for getting contact photo
     *  @param contactId by which we get photo
     */
    fun getContactPhoto(contactId: String) {
        var photo = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_baseline_person_24
        )
        var inputStream = ContactsContract.Contacts.openContactPhotoInputStream(
            context.contentResolver,
            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId.toLong())
        )
        if (inputStream != null) {
            photo = BitmapFactory.decodeStream(inputStream)
        }
        inputStream?.close()
        photoLiveData.postValue(photo)
    }

    /** This fun return photoLiveData for observing in view
     *  @return MutableLiveData<Bitmap>
     */
    fun getPhotoLivedata() : MutableLiveData<Bitmap> {
        return photoLiveData
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