package com.example.trial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>(){

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       val currentItem = items[position]
        holder.titleView.text = currentItem.title
        if(currentItem.author == "null"){
            holder.authorView.text = ""
        } else {
            holder.authorView.text = currentItem.author
        }
//        if(currentItem.url.isBlank()){
//            holder.imageView.visibility = View.GONE
//            holder.imageView.layoutParams.height = 0
//        }else{
//            holder.imageView.visibility = View.VISIBLE
//            holder.imageView.layoutParams.height = 200
            Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged() // all 3 overrides called again
    }
}

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val titleView: TextView = itemView.findViewById(R.id.title)
    val authorView: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}