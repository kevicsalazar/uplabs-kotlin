package com.kevicsalazar.uplabs

import android.app.Application

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
open class App : Application() {

    val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this) }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}