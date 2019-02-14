package com.mhemdan.popularactors.data.network.api

import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.ImagesResponse
import com.mhemdan.popularactors.data.network.ApiEndPoints
import com.mhemdan.popularactors.data.network.ApiKeys
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface PopularActorDetailsApi {

    @GET(ApiEndPoints.ACTOR_DETAILS)
    fun getActorDetails(@Path(ApiKeys.PERSON_ID) personID: Int): Single<ActorModel>

    @GET(ApiEndPoints.ACTOR_IMAGES)
    fun getActorImages(@Path(ApiKeys.PERSON_ID) personID: Int): Single<ImagesResponse>

}