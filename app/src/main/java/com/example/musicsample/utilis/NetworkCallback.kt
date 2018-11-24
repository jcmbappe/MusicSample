package com.example.musicsample.utilis

import com.example.musicsample.R
import com.example.musicsample.utilis.error.UIResolver
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException


abstract class NetworkCallback(private val resolver: UIResolver) : Callback {
    override fun onFailure(call: Call, e: IOException) {
        if (e.message.isNullOrBlank()) {
            e.message?.let { resolver.displayMessage(it) }
        } else {
            resolver.displayMessage(R.string.error_unknown)
        }
    }

    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
            onSuccess(response.body())
        } else {
            response.apply {
                onUnsuccessful(code(), message(), body())
            }
        }
    }

    abstract fun onSuccess(body: ResponseBody?)

    abstract fun onUnsuccessful(code: Int, message: String, body: ResponseBody?)
}