package com.kevicsalazar.uplabs.domain

import android.content.SharedPreferences
import com.kevicsalazar.uplabs.PerApp
import javax.inject.Inject

/**
 * Created by Kevin.
 */

@PerApp
class DataStorageHelper @Inject constructor(val pref: SharedPreferences) {

    fun hello() {

    }

}