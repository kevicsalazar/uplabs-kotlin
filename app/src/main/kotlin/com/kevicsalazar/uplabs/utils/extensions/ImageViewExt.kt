package com.kevicsalazar.uplabs.utils.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.kevicsalazar.uplabs.utils.CropCircleTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import android.graphics.drawable.BitmapDrawable


/**
 * Created by Kevin.
 */

fun ImageView.loadUrl(url: String?, transformation: Transformation? = null, cb: ((Bitmap?) -> Unit)? = null) {
    val picasso = Picasso.with(context).load(url)
    transformation?.let {
        picasso.transform(when (it) {
            Transformation.Circle -> CropCircleTransformation()
        })
    }
    picasso.into(this, object : Callback {
        override fun onSuccess() {
            cb?.invoke((drawable as BitmapDrawable).bitmap)
        }

        override fun onError() {
            e("No se pudo cargar la imagen")
        }
    })
}

fun ImageView.load(url: String){
    Picasso.with(context).load(url).into(this)
}

fun ImageView.loadCircle(url: String){
    Picasso.with(context).load(url).transform(CropCircleTransformation()).into(this)
}

enum class Transformation {

    Circle

}