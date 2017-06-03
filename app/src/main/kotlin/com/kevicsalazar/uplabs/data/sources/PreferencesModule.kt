package com.kevicsalazar.uplabs.data.sources

import android.preference.PreferenceManager
import com.kevicsalazar.uplabs.App
import dagger.Module
import dagger.Provides

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
class PreferencesModule {

    @Provides fun providePreferences(app: App) = PreferenceManager.getDefaultSharedPreferences(app)!!

}