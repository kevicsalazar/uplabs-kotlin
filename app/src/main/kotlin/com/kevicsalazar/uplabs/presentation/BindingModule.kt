package com.kevicsalazar.uplabs.presentation


import android.arch.lifecycle.ViewModel
import com.kevicsalazar.uplabs.presentation.views.MainViewModel
import com.kevicsalazar.uplabs.presentation.views.PageViewModel
import com.kevicsalazar.uplabs.presentation.views.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
abstract class BindingModule {

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(PageViewModel::class)
    abstract fun bindPageViewModel(viewModel: PageViewModel): ViewModel

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel

}