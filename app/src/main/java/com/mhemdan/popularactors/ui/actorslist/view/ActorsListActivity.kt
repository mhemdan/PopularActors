package com.mhemdan.popularactors.ui.actorslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.ui.base.view.BaseActivity
import com.mhemdan.popularactors.util.extension.addFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ActorsListActivity : BaseActivity(), HasSupportFragmentInjector {


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acors_list)

        addFragment(ActorsListFragment.newInstance() , R.id.container_fragment)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
}
