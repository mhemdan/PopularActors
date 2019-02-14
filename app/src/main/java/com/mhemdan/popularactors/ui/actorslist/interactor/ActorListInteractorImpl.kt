package com.mhemdan.popularactors.ui.actorslist.interactor

import com.google.gson.JsonElement
import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.PopularActorsResponse
import com.mhemdan.popularactors.data.network.api.PopularActorsListApi
import com.mhemdan.popularactors.ui.base.interactor.BaseInteractorImp
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorListInteractorImpl @Inject internal constructor() : BaseInteractorImp(), ActorListInteractor {
    @Inject
    lateinit var popularActorsListApi: PopularActorsListApi

    override fun getPopularActors(pageIndex: Int): Single<PopularActorsResponse> =
            popularActorsListApi.getActorsList(pageIndex)

}