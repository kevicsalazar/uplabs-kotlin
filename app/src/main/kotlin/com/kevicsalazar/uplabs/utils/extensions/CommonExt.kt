package com.kevicsalazar.uplabs.utils.extensions

import android.graphics.Color

/**
 * Created by Kevin.
 */

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

fun getDarkColor(color: Int): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(color, hsv)
    val hsvPrimary = floatArrayOf(hsv[0], hsv[1], if (hsv[2] < 0.2f) 0.2f else hsv[2])
    val hsvPrimaryDark = floatArrayOf(hsvPrimary[0], hsvPrimary[1], hsvPrimary[2] - 0.2f)
    return Color.HSVToColor(hsvPrimaryDark)
}