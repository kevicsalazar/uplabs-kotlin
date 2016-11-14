package com.kevicsalazar.uplabs.api

import com.kevicsalazar.uplabs.BuildConfig
import com.kevicsalazar.uplabs.api.ws.WebServiceIOSPosts
import com.kevicsalazar.uplabs.api.ws.WebServiceMaterialPosts
import com.kevicsalazar.uplabs.base.scopes.PerApp
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
class WebServiceModule {

    @Provides @PerApp fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
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

    @Provides @PerApp fun provideOkHttpClient(builder: OkHttpClient.Builder) = builder.addNetworkInterceptor { chain ->
        chain.proceed(chain.request().newBuilder()
                .addHeader("Accept-Charset", "utf-8")
                .addHeader("Accept", "application/json")
                .build())
    }.build()

    @Provides @PerApp @Named("material") fun provideMaterialRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .baseUrl("https://material.uplabs.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build()!!

    @Provides @PerApp @Named("ios") fun provideIOSRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .baseUrl("https://ios.uplabs.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build()!!

    @Provides @PerApp fun provideWebServiceMaterialPosts(@Named("material") retrofit: Retrofit) = retrofit.create(WebServiceMaterialPosts.Service::class.java)!!

    @Provides @PerApp fun provideWebServiceIOSPosts(@Named("ios") retrofit: Retrofit) = retrofit.create(WebServiceIOSPosts.Service::class.java)!!

}
