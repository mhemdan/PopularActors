package com.mhemdan.popularactors.di.component

import android.app.Application
import com.mhemdan.popularactors.ActorsApp
import com.mhemdan.popularactors.di.builder.ActivityBuilder
import com.mhemdan.popularactors.di.module.AppModule
import com.mhemdan.popularactors.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by m.hemdan on 10,February,2019
 * github : https://github.com/mhemdan
 */
@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (AppModule::class),
        (ActivityBuilder::class), (NetworkModule::class)]
)
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ActorsApp)
}