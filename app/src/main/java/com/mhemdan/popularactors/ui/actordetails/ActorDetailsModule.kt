package com.mhemdan.popularactors.ui.actordetails

import com.mhemdan.popularactors.ui.actordetails.interactor.ActorDetailsIneractor
import com.mhemdan.popularactors.ui.actordetails.interactor.ActorDetailsInteractorImpl
import com.mhemdan.popularactors.ui.actordetails.presenter.ActorDetailsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
@Module
class ActorDetailsModule {

    @Provides
    internal fun provideActorsListInteractor(interactor: ActorDetailsInteractorImpl): ActorDetailsIneractor = interactor

    @Provides
    internal fun provideActorListPresenter(presenter: ActorDetailsPresenter<ActorDetailsContract.View, ActorDetailsIneractor>): ActorDetailsContract.Presenter<ActorDetailsContract.View, ActorDetailsIneractor> = presenter
}