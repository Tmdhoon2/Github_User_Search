package com.example.githubusersearch.Recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusersearch.LikeEntity
import com.example.githubusersearch.R

class LikeAdapter (val context: Context, var list: List<LikeEntity>) : RecyclerView.Adapter<LikeAdapter.LikeViewHolder>(){

    class LikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val url = itemView.findViewById<ImageView>(R.id.ivlikeprofile)
        val name = itemView.findViewById<TextView>(R.id.tvlikename)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAdapter.LikeViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_like, parent, false)

        return LikeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LikeAdapter.LikeViewHolder, position: Int) {
        val like = list[position]
        holder.name.text = like.name
        Glide.with(holder.url.rootView.context)
            .load(list[position])
            .into(holder.url)
    }

    override fun getItemCount(): Int {

    }
}