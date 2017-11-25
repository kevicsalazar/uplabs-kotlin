package com.kevicsalazar.uplabs.utils.extensions

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.util.*


/**
 * Created by Kevin.
 */

/**
 * Save Data
 */

fun SharedPreferences.put(key: String, value: Any) {
    when (value) {
        is Int        -> edit().putInt(key, value).apply()
        is Long       -> edit().putLong(key, value).apply()
        is Float      -> edit().putFloat(key, value).apply()
        is Boolean    -> edit().putBoolean(key, value).apply()
        is String     -> edit().putString(key, value).apply()
        is Date       -> edit().putLong(key, value.time).apply()
        is JsonObject -> edit().putString(key, value.toString()).apply()
        is JsonArray  -> edit().putString(key, value.toString()).apply()
        else          -> edit().putString(key, Gson().toJson(value)).apply()
    }
}

/**
 * Primitive
 */

fun SharedPreferences.int(key: String, default: Int = 0): Int = getInt(key, default)

fun SharedPreferences.long(key: String, default: Long = 0L): Long = getLong(key, default)

fun SharedPreferences.float(key: String, default: Float = 0F): Float = getFloat(key, default)

fun SharedPreferences.bool(key: String, default: Boolean = false): Boolean = getBoolean(key, default)

fun SharedPreferences.string(key: String, default: String? = null): String? = getString(key, default)

/**
 * Date
 */

fun SharedPreferences.date(key: String): Date? = Date(getLong(key, 0))

/**
 * Clear Preferences
 */

fun SharedPreferences.remove(key: String) {
    edit().remove(key).apply()
}

fun SharedPreferences.clear() {
    edit().clear().apply()
}