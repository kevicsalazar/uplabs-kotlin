package com.kevicsalazar.uplabs.utils.extensions

import android.util.Log

/**
 * Created by Kevin.
 */

fun v(tag: String, message: String) {
    Log.v(tag, message)
}
fun d(tag: String, message: String) {
    Log.d(tag, message)
}
fun i(tag: String, message: String) {
    Log.i(tag, message)
}
fun w(tag: String, message: String) {
    Log.w(tag, message)
}
fun e(tag: String, message: String) {
    Log.e(tag, message)
}
fun wtf(tag: String, message: String) {
    Log.wtf(tag, message)
}
fun Any.v(message: String) {
    Log.v(this.javaClass.name, message)
}
fun Any.d(message: String) {
    Log.d(this.javaClass.name, message)
}
fun Any.i(message: String) {
    Log.i(this.javaClass.name, message)
}
fun Any.w(message: String) {
    Log.w(this.javaClass.name, message)
}
fun Any.e(message: String) {
    Log.e(this.javaClass.name, message)
}
fun Any.wtf(message: String) {
    Log.wtf(this.javaClass.name, message)
}