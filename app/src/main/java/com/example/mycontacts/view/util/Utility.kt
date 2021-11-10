package com.example.mycontacts.view.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception

class Utility {
    companion object {
        fun hideSoftKeyboard(view: View) {
            try {
                val inputMethodManager =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}