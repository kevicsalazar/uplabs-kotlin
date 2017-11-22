package com.kevicsalazar.uplabs.utils.extensions

import android.content.Context
import android.graphics.Typeface
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat

/**
 * Created by Kevin.
 */

// Activities

fun Context.colorRes(colorResId: Int) = ContextCompat.getColor(this, colorResId)

fun Context.colorStateListRes(colorResId: Int) = ContextCompat.getColorStateList(this, colorResId)!!

fun Context.intRes(intResId: Int) = resources.getInteger(intResId)

fun Context.dimenRes(dimenResId: Int) = resources.getDimensionPixelSize(dimenResId)

fun Context.stringArrayRes(arrayResId: Int) = resources.getStringArray(arrayResId)

fun Context.intArrayRes(arrayResId: Int) = resources.getIntArray(arrayResId)

fun Context.drawableRes(drawableResId: Int) = ContextCompat.getDrawable(this, drawableResId)

fun Context.typefaceFromAssets(assetPathResId: Int) = typefaceFromAssets(getString(assetPathResId))

fun Context.typefaceFromAssets(assetPath: String) = Typeface.createFromAsset(assets, assetPath)!!

// Fragments

fun Fragment.colorRes(colorResId: Int) = ContextCompat.getColor(activity, colorResId)

fun Fragment.intRes(intResId: Int) = resources.getInteger(intResId)

fun Fragment.dimenRes(dimenResId: Int) = resources.getDimensionPixelSize(dimenResId)

fun Fragment.stringArrayRes(arrayResId: Int) = resources.getStringArray(arrayResId)

fun Fragment.intArrayRes(arrayResId: Int) = resources.getIntArray(arrayResId)

fun Fragment.drawableRes(drawableResId: Int) = ContextCompat.getDrawable(context, drawableResId)

fun Fragment.typefaceFromAssets(assetPathResId: Int) = typefaceFromAssets(getString(assetPathResId))

fun Fragment.typefaceFromAssets(assetPath: String) = Typeface.createFromAsset(activity.assets, assetPath)!!