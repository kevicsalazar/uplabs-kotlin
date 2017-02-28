package com.kevicsalazar.uplabs

import android.content.Context
import com.kevicsalazar.uplabs.PerApp
import dagger.Module
import dagger.Provides

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
class AppModule(private val app: App) {

    @Provides @PerApp fun provideContext(): Context = app

}
