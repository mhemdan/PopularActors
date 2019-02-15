package com.mhemdan.popularactors.ui.base.view.loading

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mhemdan.popularactors.R
import kotlinx.android.synthetic.main.dialog_loading.*


/**
 * Created by m.hemdan on 24,December,2018
 * github : https://github.com/mhemdan
 */
class LoadingFragmentDialog : DialogFragment() {
    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_TITLE)
        val view = View.inflate(activity, R.layout.dialog_loading, null)
        dialog.setContentView(view)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startLoading()
    }

    private fun startLoading() {
        loadingView.show()
    }
}