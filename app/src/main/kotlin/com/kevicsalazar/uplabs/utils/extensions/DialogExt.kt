package com.kevicsalazar.uplabs.utils.extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.kevicsalazar.uplabs.R

/**
 * Created by Kevin.
 */

fun Context.alert(title: String, message: String, init: (AlertDialog.Builder.() -> Unit)? = null) = AlertDialog.Builder(this).apply {
    setTitle(title)
    setMessage(message)
    setPositiveButton(R.string.ok, { dialog, w -> dialog.dismiss() })
    init?.let { init() }
}

fun Fragment.alert(title: String, message: String, init: (AlertDialog.Builder.() -> Unit)? = null) = activity.alert(title, message, init)