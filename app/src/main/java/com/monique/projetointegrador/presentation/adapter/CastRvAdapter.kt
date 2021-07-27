package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.domain.Cast

class CastRvAdapter(val context: Context, val dataset: MutableList<Cast> = mutableListOf()): RecyclerView.Adapter<CastRvAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val personImg = view.findViewById<ImageView>(R.id.personImg)
        val personName = view.findViewById<TextView>(R.id.personName)
        val character = view.findViewById<TextView>(R.id.personRole)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personImg?.let{ Glide.with(context).load(Constants.BASE_URL_IMAGE.value + dataset[position].profile_path).into(it) }
        holder.personName?.text = dataset[position].name
        holder.character?.text = dataset[position].character
    }

    override fun getItemCount() = dataset.size
}