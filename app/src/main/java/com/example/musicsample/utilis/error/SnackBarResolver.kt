package com.example.musicsample.utilis.error

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackBarResolver(private val rootView : View) : UIResolver {
    override fun displayMessage(resource: Int) {
        Snackbar.make(rootView, resource, Snackbar.LENGTH_LONG).show()
    }

    override fun displayMessage(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }
}