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

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributePageFragmentInjector(): PageFragment

    @ContributesAndroidInjector
    abstract fun contributePostActivityInjector(): PostActivity

}