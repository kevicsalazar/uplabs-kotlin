package com.kevicsalazar.uplabs.utils.extensions

/**
 * Created by Kevin.
 */

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}