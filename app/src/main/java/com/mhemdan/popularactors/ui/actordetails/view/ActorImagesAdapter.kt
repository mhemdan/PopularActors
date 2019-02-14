package com.mhemdan.popularactors.ui.actordetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mhemdan.popularactors.BuildConfig
import com.mhemdan.popularactors.R
import com.mhemdan.popularactors.data.model.ImageModel
import com.mhemdan.popularactors.util.extension.launchActivity
import com.mhemdan.popularactors.utils.extensions.actorModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_actor_image.view.*

/**
 * Created by m.hemdan on 14,February,2019
 * github : https://github.com/mhemdan
 */
class ActorImagesAdapter: RecyclerView.Adapter<ActorImagesAdapter.ActorImageViewHolder>() {

    private var items = ArrayList<ImageModel>()

    fun insertItems(newItems: List<ImageModel>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorImageViewHolder =
        ActorImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_actor_image, parent, false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ActorImageViewHolder, position: Int) =
        holder.bind(items[position])
    class ActorImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ImageModel) {
            Picasso.get().load(BuildConfig.IMAGES_BASE_URL + item.file_path).into(itemView.imgActor)
            itemView.setOnClickListener {
            }
        }
    }
}