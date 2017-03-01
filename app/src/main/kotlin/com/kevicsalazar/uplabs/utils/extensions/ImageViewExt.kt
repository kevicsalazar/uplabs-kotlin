package com.kevicsalazar.uplabs.utils.extensions

import android.widget.ImageView
import com.kevicsalazar.uplabs.utils.CropCircleTransformation
import com.squareup.picasso.Picasso

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
fun ImageView.loadUrl(url: String?, transformation: Transformation? = null) {
    val picasso = Picasso.with(context).load(url)
    transformation?.let {
        picasso.transform(when (it) {
            Transformation.Circle -> CropCircleTransformation()
        })
    }
    picasso.into(this)
}

enum class Transformation {

    Circle

}