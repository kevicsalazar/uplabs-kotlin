package com.kevicsalazar.uplabs.presentation

import com.kevicsalazar.uplabs.presentation.views.MainActivity
import com.kevicsalazar.uplabs.presentation.views.PageFragment
import com.kevicsalazar.uplabs.presentation.views.PostActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
abstract class BuildersModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributePageFragmentInjector(): PageFragment

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributePostActivityInjector(): PostActivity

}