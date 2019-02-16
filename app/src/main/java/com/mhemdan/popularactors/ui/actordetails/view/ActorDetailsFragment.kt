package com.mhemdan.popularactors.ui.actordetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.data.model.ImageModel
import com.mhemdan.popularactors.ui.actordetails.ActorDetailsContract
import com.mhemdan.popularactors.ui.actordetails.interactor.ActorDetailsIneractor
import com.mhemdan.popularactors.ui.actordetails.presenter.ActorDetailsPresenter
import com.mhemdan.popularactors.ui.base.view.BaseFragment
import com.mhemdan.popularactors.utils.extensions.actorModel
import kotlinx.android.synthetic.main.fragment_actor_details.*
import javax.inject.Inject

/**
 * Created by m.hemdan on 11,February,2019
 * github : https://github.com/mhemdan
 */
class ActorDetailsFragment: BaseFragment(), ActorDetailsContract.View {

    @Inject
    internal lateinit var presenter: ActorDetailsPresenter<ActorDetailsContract.View, ActorDetailsIneractor>

    private val adapter = ActorImagesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_actor_details, container, false)

    override fun setUp() {
        presenter.attachView(this)
        arguments?.actorModel?.let {
            presenter.getActorDetails(it.id)
            adapter.actorName = it.name
        }
        listActorImages.adapter = adapter
    }

    override fun insertActorDetails(actorModel: ActorModel) {
        txtActorBio.text = actorModel.biography
        txtBirthday.text = actorModel.birthday
        txtPlaceOfBirth.text = actorModel.place_of_birth
    }

    override fun insertActorImages(images: List<ImageModel>) {
        adapter.insertItems(images)
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = ActorDetailsFragment()
    }
}