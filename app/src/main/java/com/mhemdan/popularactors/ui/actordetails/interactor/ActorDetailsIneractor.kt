package com.mhemdan.popularactors.ui.actordetails.interactor

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.ImagesResponse
import com.mhemdan.popularactors.ui.base.interactor.BaseInteractor
import io.reactivex.Single

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface ActorDetailsIneractor: BaseInteractor {
    fun getActorDetails(personID: Int): Single<ActorModel>
    fun getActorImages(personID: Int): Single<ImagesResponse>
}