package com.mhemdan.popularactors.ui.actorslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.ui.actorslist.ActorListContract
import com.mhemdan.popularactors.ui.actorslist.interactor.ActorListInteractor
import com.mhemdan.popularactors.ui.actorslist.presenter.ActorListPresenter
import com.mhemdan.popularactors.ui.base.view.BaseFragment
import com.mhemdan.popularactors.utils.extensions.searchQuery
import com.mhemdan.popularactors.utils.ui.InfiniteScrollListener
import kotlinx.android.synthetic.main.fragment_actors_list.*
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorsListFragment: BaseFragment(), ActorListContract.View {

    @Inject
    internal lateinit var presenter: ActorListPresenter<ActorListContract.View, ActorListInteractor>
    private var pageIndex = 1
    private val adapter = ActorsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_actors_list, container, false)


    override fun setUp() {
        presenter.attachView(this)

        getActors()

        listActors.adapter = adapter
           listActors .addOnScrollListener(
                InfiniteScrollListener({ getActors() }, listActors.layoutManager as GridLayoutManager)
            )
    }

    private fun getActors(){
        presenter.getPopularActors(pageIndex, arguments?.searchQuery)
    }

    override fun insertItems(items: List<ActorModel>) {
        adapter.insertItems(items)
        pageIndex++
    }

    companion object {
        fun newInstance() = ActorsListFragment()
    }
}