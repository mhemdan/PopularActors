package com.mhemdan.popularactors.ui.actordetails.presenter

import com.elmoshtarak.elmoshtarak.ui.base.presenter.BasePresenterImp
import com.mhemdan.popularactors.ui.actordetails.ActorDetailsContract
import com.mhemdan.popularactors.ui.actordetails.interactor.ActorDetailsIneractor
import com.mhemdan.popularactors.util.StateManager
import com.mhemdan.popularactors.util.rx.SchedulerProvider
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorDetailsPresenter<V : ActorDetailsContract.View, I : ActorDetailsIneractor> @Inject constructor(
    interactor: I,
    private val schedulerProvider: SchedulerProvider,
    private val stateManager: StateManager
) : BasePresenterImp<V, I>(interactor = interactor),
    ActorDetailsContract.Presenter<V, I> {

    override fun getActorDetails(personID: Int) {

        isNetConnectedOnFailurePromptUser(stateManager).not()

        view?.showLoading()
        interactor?.let {
            val disposable = it.getActorDetails(personID)
                .doOnSuccess { getActorImages(personID) }
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({ actorModel ->
                    view?.hideLoading()
                    view?.insertActorDetails(actorModel)
                }, {
                    view?.hideLoading()
                    view?.showGeneralError()
                })

            compositeDisposable?.add(disposable)
        }

    }

    private fun getActorImages(personID: Int) {

        isNetConnectedOnFailurePromptUser(stateManager).not()

        view?.showLoading()
        interactor?.let {
            val disposable = it.getActorImages(personID)
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({ imagesResponse ->
                    view?.hideLoading()
                    view?.insertActorImages(imagesResponse.profiles)
                }, {
                    view?.hideLoading()
                    view?.showGeneralError()
                })

            compositeDisposable?.add(disposable)
        }
    }
}