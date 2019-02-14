package com.mhemdan.popularactors.utils.extensions

import android.os.Bundle
import com.google.gson.Gson
import com.mhemdan.popularactors.data.model.ActorModel

/**
 * Created by m.hemdan on 09,February,2019
 * github : https://github.com/mhemdan
 */

private const val EXTRA_ACTOR_MODEL = "EXTRA_ACTOR_MODEL"
var Bundle.actorModel: ActorModel?
    get() = Gson().fromJson(getString(EXTRA_ACTOR_MODEL), ActorModel::class.java)
    set(value) {
        putString(EXTRA_ACTOR_MODEL, Gson().toJson(value))
    }
private const val EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL"
var Bundle.imageUrl: String?
    get() = getString(EXTRA_IMAGE_URL)
    set(value) {
        putString(EXTRA_IMAGE_URL, value)
    }

private const val EXTRA_ACTOR_NAME = "EXTRA_ACTOR_NAME"
var Bundle.actorName: String?
    get() = getString(EXTRA_ACTOR_NAME)
    set(value) {
        putString(EXTRA_ACTOR_NAME, value)
    }
