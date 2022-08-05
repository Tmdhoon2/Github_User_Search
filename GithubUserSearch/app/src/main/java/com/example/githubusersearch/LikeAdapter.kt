package com.example.githubusersearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class LikeAdapter(val list: ArrayList<UserLikes>) : RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    class LikeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val profile = itemView.findViewById<ImageView>(R.id.ivlikeprofile)
        val name = itemView.findViewById<TextView>(R.id.tvlikename)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAdapter.LikeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_like, parent, false)
        return LikeViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeAdapter.LikeViewHolder, position: Int) {
         Glide.with(holder.profile.rootView.context).load(list.get(position).url).into(holder.profile)
         holder.name.setText(list.get(position).userId)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}