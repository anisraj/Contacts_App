package com.example.mycontacts.view.contact_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityContactsDetailBinding
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.di.DetailsViewModelFactory
import com.example.mycontacts.di.Injector
import com.example.mycontacts.model.ContactData
import javax.inject.Inject

class ContactsDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: DetailsViewModelFactory
    private lateinit var viewModel: ContactDetailsViewModel
    private lateinit var binding: ActivityContactsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        getIntentData()
    }

    /**
     * Initialising the View using Data Binding
     */
    private fun initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts_detail)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        (application as Injector).createDetailsActSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)
            .get(ContactDetailsViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun getIntentData() {
        var contactData = intent.getSerializableExtra("contactData") as ContactData
        Toast.makeText(this, contactData.name, Toast.LENGTH_LONG).show()
    }
}