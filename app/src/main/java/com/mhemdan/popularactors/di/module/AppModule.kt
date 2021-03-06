package com.mhemdan.popularactors.di.module

import android.app.Application
import android.content.Context
import com.mhemdan.popularactors.BuildConfig
import com.mhemdan.popularactors.di.ApiKeyInfo
import com.mhemdan.popularactors.di.BaseUrlInfo
import com.mhemdan.popularactors.util.StateManager
import com.mhemdan.popularactors.util.StateManagerImpl
import com.mhemdan.popularactors.util.rx.SchedulerProvider
import com.mhemdan.popularactors.util.rx.SchedulerProviderImp
import com.mhemdan.popularactors.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by m.hemdan on 10,February,2019
 * github : https://github.com/mhemdan
 */
@Module
class AppModule {

    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @BaseUrlInfo
    internal fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(schedulerProviderImp: SchedulerProviderImp): SchedulerProvider =
        schedulerProviderImp

    @Provides
    internal fun provideStateManager(): StateManager = StateManagerImpl()

    @Provides
    @Named("networkTimeoutInSeconds")
    internal fun provideNetworkTimeoutInSeconds(): Long {
        return Constants.NETWORK_CONNECTION_TIMEOUT
    }

    @Provides
    @Named("cacheSize")
    internal fun provideCacheSize(): Long {
        return Constants.CACHE_SIZE
    }

    @Provides
    @Named("cacheMaxAge")
    internal fun provideCacheMaxAgeMinutes(): Int {
        return Constants.CACHE_MAX_AGE
    }

    @Provides
    @Named("cacheMaxStale")
    internal fun provideCacheMaxStaleDays(): Int {
        return Constants.CACHE_MAX_STALE
    }

    @Provides
    @Named("retryCount")
    fun provideApiRetryCount(): Int {
        return Constants.API_RETRY_COUNT
    }

    @Provides
    @Named("cacheDir")
    internal fun provideCacheDir(context: Context): File {
        return context.cacheDir
    }
}