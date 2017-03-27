package com.kevicsalazar.uplabs.utils.extensions

import android.graphics.Color


/**
 * Created by Kevin.
 */

typealias MaterialColor = Int

fun MaterialColor.palette() = ColorPalette(
        shadeColor(0.9),
        shadeColor(0.7),
        shadeColor(0.5),
        shadeColor(0.333),
        shadeColor(0.166),
        shadeColor(0.0),
        shadeColor(-0.125),
        shadeColor(-0.25),
        shadeColor(-0.375),
        shadeColor(-0.5),
        shadeColor(0.7),
        shadeColor(0.5),
        shadeColor(0.166),
        shadeColor(-0.25)
)

fun MaterialColor.shadeColor(percent: Double): Int {
    val f = toLong() and 0xFFFFFF
    val t = (if (percent < 0) 0 else 255).toDouble()
    val p = if (percent < 0) percent * -1 else percent
    val R = f shr 16
    val G = f shr 8 and 0x00FF
    val B = f and 0x0000FF
    val red = (Math.round((t - R) * p) + R).toInt()
    val green = (Math.round((t - G) * p) + G).toInt()
    val blue = (Math.round((t - B) * p) + B).toInt()
    return Color.rgb(red, green, blue)
}

data class ColorPalette(
        val C50: Int,
        val C100: Int,
        val C200: Int,
        val C300: Int,
        val C400: Int,
        val C500: Int,
        val C600: Int,
        val C700: Int,
        val C800: Int,
        val C900: Int,
        val A100: Int,
        val A200: Int,
        val A400: Int,
        val A700: Int
)