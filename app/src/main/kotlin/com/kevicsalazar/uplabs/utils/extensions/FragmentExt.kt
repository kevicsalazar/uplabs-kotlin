package com.kevicsalazar.uplabs.utils.extensions

import android.support.v4.app.Fragment

/**
 * Created by Kevin.
 */

fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = bundleOf(*params)
    return this
}
