package com.kevicsalazar.uplabs.utils.extensions

import android.text.Html
import android.text.Spanned

/**
 * Created by Kevin.
 */

fun String.fromHtml(): Spanned {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(this)
    }
}