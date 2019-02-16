package com.mhemdan.popularactors.ui.base.presenter

import com.mhemdan.popularactors.ui.base.interactor.BaseInteractor
import com.mhemdan.popularactors.util.StateManager
import com.mhemdan.popularactors.ui.base.view.BaseView

interface BasePresenter<V : BaseView, I : BaseInteractor> {

    fun attachView(view: V?)

    fun dropView()

    fun isNetConnectedOnFailurePromptUser(stateManager: StateManager): Boolean
}