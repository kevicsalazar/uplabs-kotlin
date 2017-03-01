package com.kevicsalazar.uplabs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
class AppModule(private val app: App) {

    @Provides @PerApp fun provideContext(): Context = app

    @Provides @PerApp fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)

}
