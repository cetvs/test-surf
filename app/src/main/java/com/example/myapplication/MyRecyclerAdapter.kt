package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Movie

class MyRecyclerAdapter(private var list: List<Movie>, var listener: OnItemClickListener) //arrayl
    : RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.item_layout,parent, false)
        return MyRecyclerHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    fun setData(lst: List<Movie>) //arrayl
    {
        list = lst
        notifyDataSetChanged();
    }

    override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
        val fragment: Movie = list[position]
        holder.bind(fragment)
    }


    inner class MyRecyclerHolder(itemView: View):
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener
    {
        private var id: Int ?= null
        private var descriptionView: TextView?= null
        private var nameView: TextView?= null
        private var imageView: ImageView?= null
        private var favImageView: ImageView?= null

        init {

            itemView.setOnClickListener(this)
            descriptionView = itemView.findViewById(R.id.tv_description)
            nameView = itemView.findViewById(R.id.tv_name)
            imageView = itemView.findViewById(R.id.iv_image)
            favImageView = itemView.findViewById(R.id.iv_fav)
        }

        fun bind(movie: Movie) {
            if(movie.favorite)
                favImageView?.setImageResource(R.drawable.fav)
            else
                favImageView?.setImageResource(R.drawable.unfav)
            imageView?.setImageResource(movie.img)
            descriptionView?.text = movie.description
            nameView?.text = movie.name
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}