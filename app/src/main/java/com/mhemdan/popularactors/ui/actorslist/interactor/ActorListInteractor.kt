package com.mhemdan.popularactors.ui.actorslist.interactor

import com.mhemdan.popularactors.data.model.PopularActorsResponse
import com.mhemdan.popularactors.ui.base.interactor.BaseInteractor
import io.reactivex.Single

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
interface ActorListInteractor: BaseInteractor {
    fun getPopularActors(pageIndex: Int): Single<PopularActorsResponse>
}