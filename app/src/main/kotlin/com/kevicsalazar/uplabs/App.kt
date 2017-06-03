package com.kevicsalazar.uplabs


import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}