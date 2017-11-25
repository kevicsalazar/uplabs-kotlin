package com.kevicsalazar.uplabs.utils.extensions

import android.content.res.Configuration
import android.support.v4.app.Fragment
import org.jetbrains.anko.bundleOf

/**
 * Created by Kevin.
 */

fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = bundleOf(*params)
    return this
}

fun Fragment.isPortrait() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
