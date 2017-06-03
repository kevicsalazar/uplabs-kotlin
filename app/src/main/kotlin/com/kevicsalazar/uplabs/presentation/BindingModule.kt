package com.kevicsalazar.uplabs.presentation


import com.kevicsalazar.uplabs.presentation.presenters.MainPresenter
import com.kevicsalazar.uplabs.presentation.presenters.PagePresenter
import com.kevicsalazar.uplabs.presentation.presenters.PostPresenter
import com.kevicsalazar.uplabs.presentation.views.MainActivity
import com.kevicsalazar.uplabs.presentation.views.PageFragment
import com.kevicsalazar.uplabs.presentation.views.PostActivity
import dagger.Binds
import dagger.Module


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
abstract class BindingModule {

    @Binds abstract fun provideMainView(activity: MainActivity): MainPresenter.View

    @Binds abstract fun providePageView(fragment: PageFragment): PagePresenter.View

    @Binds abstract fun providePostView(activity: PostActivity): PostPresenter.View

}