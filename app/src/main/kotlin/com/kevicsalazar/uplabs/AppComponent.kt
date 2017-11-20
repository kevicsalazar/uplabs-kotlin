package com.kevicsalazar.uplabs


import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}