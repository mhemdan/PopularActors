package com.mhemdan.popularactors.ui.actorslist

import com.mhemdan.popularactors.ui.actorslist.view.ActorsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
@Module
internal abstract class ActorsListFragmentProvider {

    @ContributesAndroidInjector(modules = [(ActorListModule::class)])
    internal abstract fun provideActorListFragmentFactory(): ActorsListFragment
}