package com.example.mycontacts.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycontacts.view.main.MainActViewModel

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActViewModel::class.java)) {
            return MainActViewModel(application) as T
        }
        throw IllegalArgumentException("no such class present")
    }
}