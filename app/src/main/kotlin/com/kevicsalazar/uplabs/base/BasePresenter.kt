package com.kevicsalazar.uplabs.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import java.io.IOException

/**
 * Created by Kevin Salazar
 */
abstract class BasePresenter<T : BasePresenter.BaseUI> {

    protected var ui: T? = null

    fun with(v: T): BasePresenter<T> {
        ui = v
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
     * This method will be executed when an error occurs

     * @param error is an error or exception
     */
    fun <E> onError(error: E) {
        ui?.hideProgress()
        if (error is Throwable) {
            error.printStackTrace()
            if (error is IOException) {
                ui?.showMessage("No se ha podido conectar con el servidor. Comprueba tu conexión a Internet y vuelve a intentarlo.")
            } else {
                ui?.showMessage("Ha ocurrido un error")
            }
        } else if (error is Int) {
            when (error) {
                0 -> {
                }
            }
        }
    }

    interface BaseUI {

        fun showProgress()

        fun hideProgress()

        fun showMessage(message: String)

    }

}
