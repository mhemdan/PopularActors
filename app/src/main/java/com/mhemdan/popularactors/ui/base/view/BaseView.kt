package com.mhemdan.popularactors.ui.base.view

/**
 * Created by m.hemdan on 29,January,2019
 * github : https://github.com/mhemdan
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(errorMessage: String)
    fun showInternetError()
    fun showGeneralError()
}