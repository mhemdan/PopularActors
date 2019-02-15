package com.mhemdan.popularactors.utils

/**
 * Created by m.hemdan on 13,February,2019
 * github : https://github.com/mhemdan
 */
object Constants {
    const val PAGE_SIZE = 20

    const val NETWORK_CONNECTION_TIMEOUT = 30L // 30 sec
    const val CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10 MB
    const val CACHE_MAX_AGE = 2 // 2 min
    const val CACHE_MAX_STALE = 7 // 7 day
    val API_RETRY_COUNT = 3
}