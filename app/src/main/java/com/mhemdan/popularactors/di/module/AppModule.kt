package com.mhemdan.popularactors.di.module

import com.mhemdan.popularactors.BuildConfig
import com.mhemdan.popularactors.di.ApiKeyInfo
import com.mhemdan.popularactors.di.BaseUrlInfo
import com.mhemdan.popularactors.util.StateManager
import com.mhemdan.popularactors.util.StateManagerImpl
import com.mhemdan.popularactors.util.rx.SchedulerProvider
import com.mhemdan.popularactors.util.rx.SchedulerProviderImp
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by m.hemdan on 10,February,2019
 * github : https://github.com/mhemdan
 */
@Module
class AppModule {

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
}