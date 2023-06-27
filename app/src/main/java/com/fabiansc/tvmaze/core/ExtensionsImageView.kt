package com.fabiansc.tvmaze.core

import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: String) {
    Glide.with(context).load(image).into(this)
}