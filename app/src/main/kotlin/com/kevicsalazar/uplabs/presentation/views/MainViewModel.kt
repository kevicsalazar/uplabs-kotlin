package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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

    private var livePage: MutableLiveData<Int>? = null

    fun loadPage(): LiveData<Int>? {
        if (livePage == null) {
            livePage = MutableLiveData()
        }
        return livePage
    }

    fun changePage(page: Int) {
        livePage?.value = page
    }

}