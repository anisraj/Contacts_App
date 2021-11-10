package com.example.mycontacts.view.contact_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityContactsDetailBinding
import com.example.mycontacts.di.DetailsViewModelFactory
import com.example.mycontacts.di.Injector
import com.example.mycontacts.model.ContactData
import javax.inject.Inject
import android.content.Intent
import android.net.Uri


class ContactsDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: DetailsViewModelFactory
    private lateinit var viewModel: ContactDetailsViewModel
    private lateinit var binding: ActivityContactsDetailBinding
    private lateinit var contactData: ContactData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        getIntentData()
        observeUiActions()
    }

    /**
     * Initialising the View using Data Binding
     */
    private fun initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts_detail)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding.ivPhotoBg.clipToOutline = true
        (application as Injector).createDetailsActSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)
            .get(ContactDetailsViewModel::class.java)
        binding.viewModel = viewModel
    }

    /**
     * fun for observing UI actions
     */
    private fun observeUiActions() {
        viewModel.getOnClickActions().observe(this, Observer {
            when(it) {
                ContactDetailsUiAction.CLOSE_BUTTON -> {
                    onBackPressed()
                }
                ContactDetailsUiAction.CALL_BUTTON -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:".plus(contactData.mobileNumber))
                    startActivity(intent)
                }
                ContactDetailsUiAction.MESSAGE_BUTTON -> {
                    val sendIntent = Intent(Intent.ACTION_VIEW)
                    sendIntent.data = Uri.parse("sms:".plus(contactData.mobileNumber))
                    startActivity(sendIntent)
                }
            }
        })
    }

    private fun getIntentData() {
        contactData = intent.getSerializableExtra("contactData") as ContactData
        binding.ivPhotoBg.clipToOutline = true

        /**
         * by this code we set image of contact, if null default image is set
         */
        if (contactData.id != null) {
            var photoUri = viewModel.getPhotoUri(contentResolver, contactData.id.toLong())
            if (photoUri != null) {
                binding.ivPhotoBg.setImageURI(photoUri)
            } else {
                binding.ivPhotoBg.setImageResource(R.drawable.ic_baseline_person_24)
            }
        }

        setContactUi(contactData)
    }

    private fun setContactUi(data: ContactData) {
        binding.tvName.text = data.name
        binding.tvMobileNumber.text = data.mobileNumber
    }
}