package com.mhemdan.popularactors.util

import com.blankj.utilcode.util.NetworkUtils
import javax.inject.Inject

/**
 * Created by m.hemdan on 24,December,2018
 * github : https://github.com/mhemdan
 */
class StateManagerImpl @Inject constructor() : StateManager {

    override fun isConnected(): Boolean = NetworkUtils.isConnected()
}