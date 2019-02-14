package com.elmoshtarak.elmoshtarak.ui.base.presenter


import com.mhemdan.popularactors.ui.base.interactor.BaseInteractor
import com.mhemdan.popularactors.ui.base.presenter.BasePresenter
import com.mhemdan.popularactors.ui.base.view.BaseView
import com.mhemdan.popularactors.util.StateManager
import io.reactivex.disposables.CompositeDisposable



abstract class BasePresenterImp<V : BaseView, I : BaseInteractor> internal constructor(
    protected var interactor: I?,
    protected val compositeDisposable: CompositeDisposable? = null
) : BasePresenter<V, I> {

    protected var view: V? = null

    override fun attachView(view: V?) {
        this.view = view
    }

    override fun dropView() {
        compositeDisposable?.dispose()
        this.view = null
        this.interactor = null
    }

    override fun isNetConnectedOnFailurePromptUser(stateManager: StateManager): Boolean {
        val isConnected = stateManager.isConnected()
        if (isConnected.not()) view?.showInternetError()
        return isConnected
    }
}