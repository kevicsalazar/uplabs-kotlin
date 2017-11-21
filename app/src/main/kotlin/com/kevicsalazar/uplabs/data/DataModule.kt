package com.kevicsalazar.uplabs.data

import android.preference.PreferenceManager
import com.kevicsalazar.uplabs.App
import com.kevicsalazar.uplabs.BuildConfig
import com.kevicsalazar.uplabs.data.repository.remote.RemotePostsDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
class DataModule {

    @Provides
    fun providePreferences(app: App) = PreferenceManager.getDefaultSharedPreferences(app)!!

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder
    }

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder) = builder.addNetworkInterceptor { chain ->
        chain.proceed(chain.request().newBuilder()
                .addHeader("Accept-Charset", "utf-8")
                .addHeader("Accept", "application/json")
                .build())
    }.build()!!

    @Provides
    fun provideUplabsRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .baseUrl("https://www.uplabs.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()!!

    @Provides
    fun providePostsRestService(retrofit: Retrofit) = retrofit.create(RemotePostsDataSource.Service::class.java)!!

}