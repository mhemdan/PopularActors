package com.mhemdan.popularactors.ui.actorslist.presenter

import com.elmoshtarak.elmoshtarak.ui.base.presenter.BasePresenterImp
import com.mhemdan.popularactors.ui.actorslist.ActorListContract
import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractor
import com.mhemdan.popularactors.util.StateManager
import com.mhemdan.popularactors.util.rx.SchedulerProvider
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorListPresenter<V : ActorListContract.View, I : ActorListInteractor> @Inject constructor(
    interactor: I,
    private val schedulerProvider: SchedulerProvider,
    private val stateManager: StateManager
) : BasePresenterImp<V, I>(interactor = interactor),
    ActorListContract.Presenter<V, I> {

    override fun getPopularActors(pageNumber: Int, searchQuery: String?) {
        isNetConnectedOnFailurePromptUser(stateManager).not()
        view?.showLoading()
        interactor?.let {
            val disposable = it.getPopularActors(pageNumber, searchQuery)
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({ baseResponse ->
                    view?.hideLoading()
                    view?.insertItems(baseResponse.results)
                }, {
                    view?.hideLoading()
                    view?.showGeneralError()
                })

            compositeDisposable?.add(disposable)
        }
    }
}