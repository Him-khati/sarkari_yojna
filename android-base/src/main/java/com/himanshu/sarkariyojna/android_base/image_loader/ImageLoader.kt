package com.himanshu.sarkariyojna.android_base.image_loader

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.api.load
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ImageLoader @Inject constructor() {

     fun loadImage(
        uri: Uri,
        targetImageView: ImageView
    ) {
        targetImageView.load(
            uri = uri,
            builder ={
                crossfade(true)
                build()
            }
        )
    }

    fun loadImage(
        @DrawableRes imageRes: Int,
        targetImageView: ImageView,
    ) {
        targetImageView.load(
            drawableResId = imageRes,
            builder ={
                crossfade(true)
                build()
            }
        )
    }
}