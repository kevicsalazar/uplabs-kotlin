package com.kevicsalazar.uplabs.presentation

import com.kevicsalazar.uplabs.presentation.views.*
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(BindingModule::class))
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(BindingModule::class))
    abstract fun contributePageFragmentInjector(): PageFragment

    @ContributesAndroidInjector(modules = arrayOf(BindingModule::class))
    abstract fun contributePostActivityInjector(): PostActivity

}