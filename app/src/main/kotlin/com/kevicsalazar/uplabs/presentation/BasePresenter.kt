package com.kevicsalazar.uplabs.presentation

import java.io.IOException

/**
 * Created by Kevin Salazar
 */
abstract class BasePresenter<T : BasePresenter.BaseView> {

    protected var view: T? = null

    fun with(v: T): BasePresenter<T> {
        view = v
        return this
    }

    /**
     * This method will be executed on
     * [AppCompatActivity.onResume] in case presenter is attached to activity
     * [Fragment.onResume]  in case presenter is attached to fragment
     */
    abstract fun onResume()

    /**
     * This method will be executed on
     * [AppCompatActivity.onPause] in case presenter is attached to activity
     * [Fragment.onPause]  in case presenter is attached to fragment
     */
    abstract fun onPause()

    /**
     * This method will be executed on
     * [AppCompatActivity.onDestroy] in case presenter is detached to activity
     * [Fragment.onDestroy] in case presenter is detached to fragment
     */
    abstract fun onDestroy()

    /**
     * Base Interface for View
     */

    interface BaseView {

        fun showProgress()

        fun hideProgress()

        fun showMessage(title: String, message: String)

    }

}
