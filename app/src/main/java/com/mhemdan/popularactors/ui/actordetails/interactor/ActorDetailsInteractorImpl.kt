package com.mhemdan.popularactors.ui.actordetails.interactor

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.ImagesResponse
import com.mhemdan.popularactors.data.network.api.PopularActorDetailsApi
import com.mhemdan.popularactors.ui.base.interactor.BaseInteractorImp
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorDetailsInteractorImpl @Inject internal constructor() : BaseInteractorImp(), ActorDetailsIneractor {

    @Inject
    lateinit var popularActorDetailsApi: PopularActorDetailsApi

    override fun getActorDetails(actorID: Int): Single<ActorModel> =
        popularActorDetailsApi.getActorDetails(actorID)

    override fun getActorImages(actorID: Int): Single<ImagesResponse> =
            popularActorDetailsApi.getActorImages(actorID)
}