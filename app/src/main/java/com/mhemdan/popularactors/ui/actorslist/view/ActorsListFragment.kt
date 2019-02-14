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
import kotlinx.android.synthetic.main.fragment_actors_list.*
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorsListFragment: BaseFragment(), ActorListContract.View {

    @Inject
    internal lateinit var presenter: ActorListPresenter<ActorListContract.View, ActorListInteractor>
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    val adapter = ActorsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_actors_list, container, false)


    override fun setUp() {
        presenter.attachView(this)
        presenter.getPopularActors(0)
           listActors.addOnScrollListener(RecyclerView.OnScrollListener{

           })
        listActors.adapter = adapter
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    listActors.removeOnScrollListener(scrollListener)
                }
            }
        }
        listActors.addOnScrollListener(scrollListener)
    }

    override fun insertItems(items: List<ActorModel>) {
        adapter.insertItems(items)
    }

    companion object {
        fun newInstance() = ActorsListFragment()
    }
}