package com.example.mycontacts.view.contact_detail

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityContactsDetailBinding
import com.example.mycontacts.databinding.ActivityMainBinding
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
        observeImage()
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
        if (contactData.id != null) {
            viewModel.getContactPhoto(contactData.id)
        }
        setContactUi(contactData)
    }

    private fun observeImage() {
        viewModel.getPhotoLivedata().observe(this, Observer {
            binding.ivPhotoBg.setImageBitmap(it)
        })
    }

    private fun setContactUi(data: ContactData) {
        binding.tvName.text = data.name
        binding.tvMobileNumber.text = data.mobileNumber
    }
}