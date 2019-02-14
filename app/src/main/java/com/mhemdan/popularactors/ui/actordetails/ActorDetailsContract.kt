package com.mhemdan.popularactors.ui.actordetails

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.ImageModel
import com.mhemdan.popularactors.ui.actordetails.interactor.ActorDetailsIneractor
import com.mhemdan.popularactors.ui.base.presenter.BasePresenter
import com.mhemdan.popularactors.ui.base.view.BaseView

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface ActorDetailsContract {
    interface Presenter<V : View, I : ActorDetailsIneractor> : BasePresenter<V, I> {
        fun getActorDetails(personID: Int)
    }

    interface View : BaseView {
        fun insertActorDetails(actorModel: ActorModel)
        fun insertActorImages(images: List<ImageModel>)
    }
}