package com.kevicsalazar.uplabs.utils.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.kevicsalazar.uplabs.utils.CropCircleTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
fun ImageView.loadUrl(url: String?, transformation: Transformation? = null, cb: ((Bitmap?) -> Unit)? = null) {
    val picasso = Picasso.with(context).load(url)
    transformation?.let {
        picasso.transform(when (it) {
            Transformation.Circle -> CropCircleTransformation()
        })
    }
    picasso.into(object : Target{
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }

        override fun onBitmapFailed(errorDrawable: Drawable?) {

        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            cb?.invoke(bitmap)
            setImageBitmap(bitmap)
        }
    })
}

enum class Transformation {

    Circle

}