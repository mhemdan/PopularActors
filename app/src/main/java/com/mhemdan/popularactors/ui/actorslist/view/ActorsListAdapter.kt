package com.mhemdan.popularactors.ui.actorslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhemdan.popularactors.BuildConfig.IMAGES_BASE_URL
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.data.model.ActorModel
import com.mhemdan.popularactors.ui.actordetails.view.ActorDetailsActivity
import com.mhemdan.popularactors.util.extension.launchActivity
import com.mhemdan.popularactors.utils.extensions.actorModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_actor.view.*

/**
 * Created by m.hemdan on 13,February,2019
 * github : https://github.com/mhemdan
 */
class ActorsListAdapter : RecyclerView.Adapter<ActorsListAdapter.ActorViewHolder>() {
    private var items = ArrayList<ActorModel>()

    fun insertItems(newItems: List<ActorModel>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_actor, parent, false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.bind(items[position])

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ActorModel) {
            Picasso.get().load(IMAGES_BASE_URL + item.profile_path).into(itemView.imgActor)
            itemView.txtActorName.text = item.name
            itemView.setOnClickListener {
                itemView.context.launchActivity<ActorDetailsActivity> {
                    putExtras(Bundle().apply {
                        this.actorModel = item
                    })
                }
            }
        }
    }
}