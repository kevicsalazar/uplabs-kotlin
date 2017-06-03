package com.kevicsalazar.uplabs

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
class AppModule {

    /**
     * App Context
     */

    @Provides fun provideContext(app: App): Context = app

}
