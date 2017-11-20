package com.kevicsalazar.uplabs

import android.content.Context
import com.kevicsalazar.uplabs.data.DataModule
import com.kevicsalazar.uplabs.presentation.BindingModule
import com.kevicsalazar.uplabs.presentation.BuildersModule
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module(includes = arrayOf(
        DataModule::class,
        BindingModule::class,
        BuildersModule::class,
        AndroidSupportInjectionModule::class))
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app

}
