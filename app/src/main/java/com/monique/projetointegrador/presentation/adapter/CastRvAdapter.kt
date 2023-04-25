package com.monique.projetointegrador.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.domain.model.Cast
import com.monique.projetointegrador.presentation.utils.extensions.loadImage
import de.hdodenhof.circleimageview.CircleImageView

class CastRvAdapter : ListAdapter<Cast, CastRvAdapter.ViewHolder>(
    CastRvDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cast, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Cast) {
            itemView.findViewById<CircleImageView>(R.id.personImg).loadImage(
                Constants.BASE_URL_IMAGE.value + item.profilePath
            )
            itemView.findViewById<TextView>(R.id.personName).text = item.name
            itemView.findViewById<TextView>(R.id.personRole).text = item.character
        }
    }

    class CastRvDiffCallback : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(
            oldItem: Cast,
            newItem: Cast
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Cast,
            newItem: Cast
        ): Boolean = oldItem == newItem
    }
}