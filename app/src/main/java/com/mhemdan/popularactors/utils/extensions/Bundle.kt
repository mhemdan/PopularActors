package com.mhemdan.popularactors.utils.extensions

import android.os.Bundle
import com.google.gson.Gson
import com.mhemdan.popularactors.data.model.ActorModel

/**
 * Created by m.hemdan on 09,February,2019
 * github : https://github.com/mhemdan
 */

private const val EXTRA_ACTOR_MODEL= "EXTRA_ACTOR_MODEL"
var Bundle.actorModel: ActorModel?
    get() = Gson().fromJson(getString(EXTRA_ACTOR_MODEL), ActorModel::class.java)
    set(value) {
        putString(EXTRA_ACTOR_MODEL, Gson().toJson(value))
    }

