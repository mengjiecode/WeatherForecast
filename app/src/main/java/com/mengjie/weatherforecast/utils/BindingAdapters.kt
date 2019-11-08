package com.mengjie.weatherforecast.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("app:weatherIcon")
fun weatherIcon(view: ImageView, weatherIconUrl: String) {
    Glide.with(view.context)
        .load(weatherIconUrl).apply(RequestOptions())
        .into(view)
}