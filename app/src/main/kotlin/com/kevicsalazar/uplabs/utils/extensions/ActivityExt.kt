package com.kevicsalazar.uplabs.utils.extensions

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity

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

@SuppressLint("NewApi")
fun AppCompatActivity.setStatusBarColor(color: Int) {
    if (lollipopOrNewer()) window.statusBarColor = color
}

fun AppCompatActivity.showHomeButton() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}