package com.mhemdan.popularactors.ui.actorslist

import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractor
import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractorImpl
import com.mhemdan.popularactors.ui.actorslist.presenter.ActorListPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
@Module
class ActorListModule {

    @Provides
    internal fun provideActorsListInteractor(interactor: ActorListInteractorImpl): ActorListInteractor = interactor

    @Provides
    internal fun provideActorListPresenter(presenter: ActorListPresenter<ActorListContract.View, ActorListInteractor>): ActorListContract.Presenter<ActorListContract.View, ActorListInteractor> = presenter
}