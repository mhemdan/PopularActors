package com.mhemdan.popularactors.di.module

import com.mhemdan.popularactors.data.network.api.PopularActorDetailsApi
import com.mhemdan.popularactors.data.network.api.PopularActorsListApi
import com.mhemdan.popularactors.di.ApiKeyInfo
import com.mhemdan.popularactors.di.BaseUrlInfo
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Named


/**
 * Created by m.hemdan on 10,February,2019
 * github : https://github.com/mhemdan
 */
@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideActorsListApi(retrofit: Retrofit): PopularActorsListApi =
         retrofit.create(PopularActorsListApi::class.java)

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideActorDetailsApi(retrofit: Retrofit): PopularActorDetailsApi =
        retrofit.create(PopularActorDetailsApi::class.java)


    @Provides
    @Named("API_KEY_INTERCEPTOR")
    @JvmStatic
    internal fun provideApiKeyInterceptor(@ApiKeyInfo apiKey: String) = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build()
            request = request.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }

    @Provides
    @JvmStatic
    internal fun provideOkHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @JvmStatic
    internal fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, @Named("API_KEY_INTERCEPTOR") apiKeyInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().
            addInterceptor(loggingInterceptor).
            addInterceptor(apiKeyInterceptor).build()

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient, @BaseUrlInfo baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}