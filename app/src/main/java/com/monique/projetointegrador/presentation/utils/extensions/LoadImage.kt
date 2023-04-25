package com.monique.projetointegrador.presentation.utils.extensions

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}