package com.mhemdan.popularactors.ui.base.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.ui.base.view.loading.LoadingFragmentDialog
import com.mhemdan.popularactors.util.extension.addFragment
import com.mhemdan.popularactors.util.extension.findFragment
import com.mhemdan.popularactors.util.extension.removeFragment
import com.tapadoo.alerter.Alerter
import dagger.android.AndroidInjection

/**
 * Created by m.hemdan on 29,January,2019
 * github : https://github.com/mhemdan
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }


    override fun showLoading() {
        if (findFragment(LoadingFragmentDialog::class.java.simpleName) == null) {
            addFragment(LoadingFragmentDialog())
        }
    }

    override fun hideLoading() {
        findFragment(LoadingFragmentDialog::class.java.simpleName)?.let { removeFragment(it) }
    }

    override fun showInternetError() {
        showError(getString(R.string.app_internet_connection_error))
    }

    override fun showError(errorMessage: String) {
        Alerter.create(this).apply {
            setText(errorMessage)
            setBackgroundColorRes(R.color.app_error_color)
            show()
        }
    }

    override fun showGeneralError() {
        showError(getString(R.string.app_general_error))
    }

    private fun performDI() = AndroidInjection.inject(this)

}