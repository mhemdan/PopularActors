package com.mhemdan.popularactors.ui.actordetails

import com.mhemdan.popularactors.ui.actordetails.view.ActorDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by m.hemdan on 14,February,2019
 * github : https://github.com/mhemdan
 */
@Module
internal abstract class ActorDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = [(ActorDetailsModule::class)])
    internal abstract fun provideActorListFragmentFactory(): ActorDetailsFragment
}