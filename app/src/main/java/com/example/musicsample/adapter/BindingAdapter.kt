package com.example.musicsample.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.example.musicsample.R

@BindingAdapter("imageFromUrl")
fun setPictureURL(imageView: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .error(R.mipmap.ic_launcher)
        .placeholder(R.mipmap.ic_launcher)
        .into(imageView)
}


@BindingAdapter("visibleIfTrue")
fun setVisibility(view: View, isVisible: Boolean?) {
    if (isVisible == null || isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
