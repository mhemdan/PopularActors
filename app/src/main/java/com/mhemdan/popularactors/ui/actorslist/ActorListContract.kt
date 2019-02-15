package com.mhemdan.popularactors.ui.actorslist

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractor
import com.mhemdan.popularactors.ui.base.presenter.BasePresenter
import com.mhemdan.popularactors.ui.base.view.BaseView

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface ActorListContract {
    interface Presenter<V : View, I : ActorListInteractor> : BasePresenter<V, I> {
        fun getPopularActors(pageNumber: Int, searchQuery: String? = "")
    }

    interface View : BaseView {
        fun insertItems(items: List<ActorModel>)
        fun showEmptyResults()
    }
}