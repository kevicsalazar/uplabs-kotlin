package com.kevicsalazar.uplabs.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import java.io.Serializable

/**
 * Created by Kevin.
 */

fun Context.colorRes(colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}
fun Context.colorStateListRes(colorResId: Int): ColorStateList {
    return ContextCompat.getColorStateList(this, colorResId)
}
fun Context.intRes(intResId: Int): Int {
    return resources.getInteger(intResId)
}
fun Context.dimenRes(dimenResId: Int): Int {
    return resources.getDimensionPixelSize(dimenResId)
}
fun Context.stringArrayRes(arrayResId: Int): Array<String> {
    return resources.getStringArray(arrayResId)
}
fun Context.intArrayRes(arrayResId: Int): IntArray {
    return resources.getIntArray(arrayResId)
}
fun Context.drawableRes(drawableResId: Int): Drawable {
    return ContextCompat.getDrawable(this, drawableResId)
}
fun Context.typefaceFromAssets(assetPathResId: Int): Typeface {
    return typefaceFromAssets(getString(assetPathResId))
}
fun Context.typefaceFromAssets(assetPath: String): Typeface {
    return Typeface.createFromAsset(assets, assetPath)
}
fun Fragment.colorRes(colorResId: Int): Int {
    return ContextCompat.getColor(activity, colorResId)
}
fun Fragment.intRes(intResId: Int): Int {
    return resources.getInteger(intResId)
}
fun Fragment.dimenRes(dimenResId: Int): Int {
    return resources.getDimensionPixelSize(dimenResId)
}
fun Fragment.stringArrayRes(arrayResId: Int): Array<String> {
    return resources.getStringArray(arrayResId)
}
fun Fragment.intArrayRes(arrayResId: Int): IntArray {
    return resources.getIntArray(arrayResId)
}
fun Fragment.drawableRes(drawableResId: Int): Drawable {
    return ContextCompat.getDrawable(context, drawableResId)
}
fun Fragment.typefaceFromAssets(assetPathResId: Int): Typeface {
    return typefaceFromAssets(getString(assetPathResId))
}
fun Fragment.typefaceFromAssets(assetPath: String): Typeface {
    return Typeface.createFromAsset(activity.assets, assetPath)
}

fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = bundleOf(*params)
    return this
}

fun bundleOf(vararg params: Pair<String, Any>): Bundle {
    val b = Bundle()
    for (p in params) {
        val (k, v) = p
        when (v) {
            is Boolean -> b.putBoolean(k, v)
            is Byte -> b.putByte(k, v)
            is Char -> b.putChar(k, v)
            is Short -> b.putShort(k, v)
            is Int -> b.putInt(k, v)
            is Long -> b.putLong(k, v)
            is Float -> b.putFloat(k, v)
            is Double -> b.putDouble(k, v)
            is String -> b.putString(k, v)
            is CharSequence -> b.putCharSequence(k, v)
            is Parcelable -> b.putParcelable(k, v)
            is Serializable -> b.putSerializable(k, v)
            is BooleanArray -> b.putBooleanArray(k, v)
            is ByteArray -> b.putByteArray(k, v)
            is CharArray -> b.putCharArray(k, v)
            is DoubleArray -> b.putDoubleArray(k, v)
            is FloatArray -> b.putFloatArray(k, v)
            is IntArray -> b.putIntArray(k, v)
            is LongArray -> b.putLongArray(k, v)
            is Array<*> -> {
                @Suppress("UNCHECKED_CAST")
                when {
                    v.isArrayOf<Parcelable>() -> b.putParcelableArray(k, v as Array<out Parcelable>)
                    v.isArrayOf<CharSequence>() -> b.putCharSequenceArray(k, v as Array<out CharSequence>)
                    v.isArrayOf<String>() -> b.putStringArray(k, v as Array<out String>)
                    else -> throw Throwable("Unsupported bundle component (${v.javaClass})")
                }
            }
            is ShortArray -> b.putShortArray(k, v)
            is Bundle -> b.putBundle(k, v)
            else -> throw Throwable("Unsupported bundle component (${v.javaClass})")
        }
    }

    return b
}