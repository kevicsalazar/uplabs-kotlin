package com.kevicsalazar.uplabs.base.ext

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
fun ImageView.loadUrl(url: String?, transformation: Transformation? = null) {
    val picasso = Picasso.with(context).load(url)
    if (transformation != null) {
        picasso.transform(transformation)
    }
    picasso.into(this)
}