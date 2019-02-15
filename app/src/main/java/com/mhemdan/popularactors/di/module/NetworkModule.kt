package com.mhemdan.popularactors.di.module

import com.mhemdan.popularactors.data.network.api.PopularActorDetailsApi
import com.mhemdan.popularactors.data.network.api.PopularActorsListApi
import com.mhemdan.popularactors.di.ApiKeyInfo
import com.mhemdan.popularactors.di.BaseUrlInfo
import com.mhemdan.popularactors.util.StateManager
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Named


/**
 * Created by m.hemdan on 10,February,2019
 * github : https://github.com/mhemdan
 */
@Module
object NetworkModule {

    private const val HTTP_CACHE_PATH = "http-cache"
    private const val CACHE_CONTROL = "Cache-Control"
    private const val PRAGMA = "Pragma"

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
    @Named("apiKeyInterceptor")
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
    @Named("cache")
    @JvmStatic
    fun provideCache(@Named("cacheDir") cacheDir: File, @Named("cacheSize") cacheSize: Long): Cache =
        Cache(File(cacheDir.path, HTTP_CACHE_PATH), cacheSize)


    @Provides
    @Named("cacheInterceptor")
    @JvmStatic
    fun provideCacheInterceptor(@Named("cacheMaxAge") maxAgeMin: Int) =
        object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                    .maxAge(maxAgeMin, TimeUnit.MINUTES)
                    .build()

                return response.newBuilder()
                    .removeHeader(PRAGMA)
                    .removeHeader(CACHE_CONTROL)
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }


    @Provides
    @Named("offlineInterceptor")
    @JvmStatic
    fun provideOfflineCacheInterceptor(stateManager: StateManager, @Named("cacheMaxStale") maxStaleDay: Int) =
        object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()

                request = if (!stateManager.isConnected()) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(maxStaleDay, TimeUnit.DAYS)
                        .build()

                    request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
                } else {
                    request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build()
                }

                return chain.proceed(request)
            }
        }


    @Provides
    @Named("retryInterceptor")
    @JvmStatic
    fun provideRetryInterceptor(@Named("retryCount") retryCount: Int) = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            var response: Response? = null
            var exception: IOException? = null

            var tryCount = 0
            while (tryCount < retryCount && (null == response || !response.isSuccessful)) {
                // retry the request
                try {
                    response = chain.proceed(request)
                } catch (e: IOException) {
                    exception = e
                } finally {
                    tryCount++
                }
            }

            // throw last exception
            if (null == response && null != exception) {
                throw exception
            }


            // otherwise just pass the original response on
            return response!!
        }
    }


    @Provides
    @JvmStatic
    internal fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named("apiKeyInterceptor") apiKeyInterceptor: Interceptor,
        @Named("networkTimeoutInSeconds") networkTimeoutInSeconds: Long,
        @Named("cache") cache: Cache,
        @Named("cacheInterceptor") cacheInterceptor: Interceptor,
        @Named("offlineInterceptor") offlineCacheInterceptor: Interceptor,
        @Named("retryInterceptor") retryInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(offlineCacheInterceptor)
            .addInterceptor(retryInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .cache(cache)
            .connectTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS)
            .build()

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