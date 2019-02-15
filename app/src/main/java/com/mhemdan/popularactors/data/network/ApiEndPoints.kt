package com.mhemdan.popularactors.data.network

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
object ApiEndPoints {
    const val ACTORS_LIST = "person/popular"
    const val ACTOR_DETAILS = "person/{${ApiKeys.PERSON_ID}}"
    const val ACTOR_IMAGES = "person/{${ApiKeys.PERSON_ID}}/images"
    const val ACTOR_SEARCH = "search/person"
}