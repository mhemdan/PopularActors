package com.mhemdan.popularactors.data.network.api

import com.mhemdan.popularactors.data.model.PopularActorsResponse
import com.mhemdan.popularactors.data.network.ApiEndPoints
import com.mhemdan.popularactors.data.network.ApiKeys.PAGE_INDEX
import com.mhemdan.popularactors.data.network.ApiKeys.SEARCH_QUERY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface PopularActorsListApi {

    @GET(ApiEndPoints.ACTORS_LIST)
    fun getActorsList(@Query(PAGE_INDEX) pageIndex: Int): Single<PopularActorsResponse>

    @GET(ApiEndPoints.ACTOR_SEARCH)
    fun searchActors(@Query(SEARCH_QUERY) searchQuery: String, @Query(PAGE_INDEX) pageIndex: Int): Single<PopularActorsResponse>

}