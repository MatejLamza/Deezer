package com.example.helpers

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class UIHelper {

    companion object {
        fun setImage(context: Context, imageView: ImageView?, picture: String) {
            Glide.with(context)
                .load(picture)
                .into(imageView!!)
        }

    }
}