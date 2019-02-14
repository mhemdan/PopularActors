package com.mhemdan.popularactors.ui.actordetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mhemdan.popularactors.BuildConfig.IMAGES_BASE_URL
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.ui.base.view.BaseActivity
import com.mhemdan.popularactors.util.extension.addFragment
import com.mhemdan.popularactors.utils.extensions.actorModel
import com.mhemdan.popularactors.utils.extensions.withArgs
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_actor_details.*
import javax.inject.Inject

class ActorDetailsActivity : BaseActivity(), HasSupportFragmentInjector {


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_details)

        setToolbar()

        addFragment(ActorDetailsFragment.newInstance().withArgs {
            this.actorModel = intent.extras.actorModel
        } , R.id.container_fragment)

    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbarLayout.title = intent.extras?.actorModel?.name
        Glide.with(this).load( IMAGES_BASE_URL + intent.extras?.actorModel?.profile_path).into(imgActor)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
