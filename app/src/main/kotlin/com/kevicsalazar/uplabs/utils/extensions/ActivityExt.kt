package com.kevicsalazar.uplabs.utils.extensions

import android.app.Activity
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.jetbrains.anko.internals.AnkoInternals.createIntent

/**
 * Created by Kevin.
 */

fun FragmentActivity.replaceContentFragment(containerViewId: Int, fragment: Fragment?): Fragment? {
    supportFragmentManager
            ?.beginTransaction()
            ?.replace(containerViewId, fragment)
            ?.commit()
    return fragment
}

fun AppCompatActivity.setStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = color
    }
}

fun AppCompatActivity.showHomeButton() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

inline fun <reified T : Activity> AppCompatActivity.startActivity(params: List<Pair<String, Any>>, elements: List<Pair<View, String>>) {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *elements.map { android.support.v4.util.Pair(it.first, it.second) }.toTypedArray())
    ActivityCompat.startActivity(this, createIntent(this, T::class.java, params.toTypedArray()), options.toBundle())
}