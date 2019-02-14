package com.mhemdan.popularactors.ui.base.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * Created by m.hemdan on 29,January,2019
 * github : https://github.com/mhemdan
 */
abstract class BaseFragment : Fragment(), BaseView {
    var parentActivity: BaseActivity? = null
        private set

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun showInternetError() {
        parentActivity?.showInternetError()
    }
    override fun hideLoading() {
        parentActivity?.hideLoading()
    }

    override fun showLoading() {
        parentActivity?.showLoading()
    }

    override fun showError(errorMessage: String) {
        parentActivity?.showError(errorMessage)
    }

    override fun showGeneralError() {
        parentActivity?.showGeneralError()
    }

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    abstract fun setUp()
}