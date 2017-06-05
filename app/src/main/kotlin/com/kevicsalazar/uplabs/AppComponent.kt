package com.kevicsalazar.uplabs


import com.kevicsalazar.uplabs.data.DataModule
import com.kevicsalazar.uplabs.data.sources.CloudModule
import com.kevicsalazar.uplabs.data.sources.PreferencesModule
import com.kevicsalazar.uplabs.presentation.BindingModule
import com.kevicsalazar.uplabs.presentation.BuildersModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Component(modules = arrayOf(
        AppModule::class,
        DataModule::class,
        CloudModule::class,
        PreferencesModule::class,
        BuildersModule::class,
        BindingModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}