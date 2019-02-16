package com.mhemdan.popularactors.di.builder

import com.mhemdan.popularactors.ui.actordetails.ActorDetailsFragmentProvider
import com.mhemdan.popularactors.ui.actordetails.view.ActorDetailsActivity
import com.mhemdan.popularactors.ui.actorslist.ActorsListFragmentProvider
import com.mhemdan.popularactors.ui.actorslist.view.ActorsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by m.hemdan on 10,February,2019
 * github : https://github.com/mhemdan
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(ActorsListFragmentProvider::class)])
    abstract fun bindActorListActivity(): ActorsListActivity

    @ContributesAndroidInjector(modules = [(ActorDetailsFragmentProvider::class)])
    abstract fun bindActorDetailsActivity(): ActorDetailsActivity
}