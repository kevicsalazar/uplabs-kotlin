package com.kevicsalazar.uplabs.utils.extensions

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import com.kevicsalazar.uplabs.utils.CropCircleTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


/**
 * Created by Kevin.
 */

fun ImageView.load(url: String?, cb: ((Bitmap?) -> Unit)? = null) {
    Picasso.with(context).load(url)
            .into(this, object : Callback {
                override fun onSuccess() {
                    cb?.invoke((drawable as BitmapDrawable).bitmap)
                }

                override fun onError() {
                    Log.e("Error", "No se pudo cargar la imagen")
                }
            })
}

fun ImageView.loadCircle(url: String?, cb: ((Bitmap?) -> Unit)? = null) {
    Picasso.with(context).load(url)
            .transform(CropCircleTransformation())
            .into(this, object : Callback {
                override fun onSuccess() {
                    cb?.invoke((drawable as BitmapDrawable).bitmap)
                }

                override fun onError() {
                    Log.e("Error", "No se pudo cargar la imagen")
                }
            })
}