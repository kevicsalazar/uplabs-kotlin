package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kevicsalazar.uplabs.presentation.PerActivity
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@PerActivity
class MainViewModel @Inject constructor() : ViewModel() {

    fun doSomething() {
        Log.e("MainViewModel: ", "${hashCode()}")
    }

}