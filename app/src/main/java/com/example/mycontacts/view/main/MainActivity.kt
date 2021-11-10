package com.example.mycontacts.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.di.Injector
import com.example.mycontacts.di.MainViewModelFactory
import com.example.mycontacts.model.ContactData
import com.example.mycontacts.view.contact_detail.ContactsDetailActivity
import com.example.mycontacts.view.util.Utility
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ContactClickListener {

    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainActViewModel
    private lateinit var binding: ActivityMainBinding
    private var contactsList = ArrayList<ContactData>()
    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        getContacts()
        initAdapter()
        observerContactsLiveData()
    }

    /**
     * Initialising the View using Data Binding
     */
    private fun initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        (application as Injector).createMainActSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)
            .get(MainActViewModel::class.java)
        binding.viewModel = viewModel
        binding.etSearchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS) !=
                    PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@MainActivity, Array(1){ Manifest.permission.READ_CONTACTS}, 111)
                } else {
                    viewModel.readContacts(s.toString())
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    /**
     * fun for checking permission and getting contacts
     */
    private fun getContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, Array(1){ Manifest.permission.READ_CONTACTS}, 111)
        } else {
            viewModel.readContacts("")
        }
    }

    /** fun for initializing contacts recyclerview adapter
     *
     */
    private fun initAdapter() {
        adapter = ContactsAdapter(contactsList, this)
        binding.rvContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvContacts.adapter = adapter
    }

    /**  fun for observing contacts data
     *
     */
    private fun observerContactsLiveData() {
        viewModel.getContactsDataObject().observe(this, Observer {
            contactsList.clear()
            contactsList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.getAnimate().observe(this, Observer {
            binding.etSearchView.setText("")
            Utility.hideSoftKeyboard(binding.etSearchView)
            binding.etSearchView.clearFocus()
            binding.rvContacts.smoothScrollToPosition(0)
            binding.ivRefresh.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation))
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            viewModel.readContacts("")
        }
    }

    /** fun for contacts adapter click listener
     * @param contactData contact data
     */
    override fun onClick(contactData: ContactData) {
        val intent = Intent(this, ContactsDetailActivity::class.java)
        intent.putExtra("contactData", contactData)
        startActivity(intent)
    }
}