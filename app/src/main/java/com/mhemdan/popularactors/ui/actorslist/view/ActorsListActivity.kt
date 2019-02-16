package com.mhemdan.popularactors.ui.actorslist.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.ui.base.view.BaseActivity
import com.mhemdan.popularactors.util.extension.addFragment
import com.mhemdan.popularactors.util.extension.addFragmentWithBackStack
import com.mhemdan.popularactors.utils.extensions.searchQuery
import com.mhemdan.popularactors.utils.extensions.withArgs
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_acors_list.*
import javax.inject.Inject

class ActorsListActivity : BaseActivity(), HasSupportFragmentInjector, SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acors_list)

        setToolbar()

        addFragment(ActorsListFragment.newInstance(), R.id.container_fragment)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        addFragmentWithBackStack(ActorsListFragment.newInstance().withArgs {
            this.searchQuery = query
        }, R.id.container_fragment)

        return true
    }

    override fun onClose(): Boolean {
        onBackPressed()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
