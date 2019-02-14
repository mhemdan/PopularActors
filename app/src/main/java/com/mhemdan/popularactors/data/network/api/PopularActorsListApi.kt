package com.mhemdan.popularactors.data.network.api

import com.mhemdan.popularactors.data.model.PopularActorsResponse
import com.mhemdan.popularactors.data.network.ApiEndPoints
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface PopularActorsListApi {

    @GET(ApiEndPoints.ACTORS_LIST)
    fun getActorsList(): Single<PopularActorsResponse>

}