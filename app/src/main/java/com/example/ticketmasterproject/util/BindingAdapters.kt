package com.example.ticketmasterproject.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ticketmasterproject.domain.Image

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, listurl: String) {
    //var strUrl: String = listurl[0].url
    Glide.with(imageView.context).load(listurl).into(imageView)
}

/*@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}*/
