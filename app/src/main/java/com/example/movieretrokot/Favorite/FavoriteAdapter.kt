package com.example.movieretrokot.Favorite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieretrokot.R
import com.example.movieretrokot.Movie.Movie
import kotlinx.android.synthetic.main.list_item_favorite.view.*


class FavoriteAdapter(val context: Context): ListAdapter<Movie,FavoriteAdapter.FavViewHolder>(MovieItemCallback) {

    //private var mList = emptyList<com.example.movieretrokot.Data.Movie>()

    companion object MovieItemCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_favorite, parent, false)
        return FavViewHolder(itemView)
    }


    override fun onBindViewHolder(itemViewHolder: FavViewHolder, position: Int) {
        val movie = getItem(position)
        Log.d("movieInfo",""+movie)
        itemViewHolder.setData(movie, position)
    }

    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        private var currentPosition: Int = -1
        private var currentCity: Movie?   = null

        private val txvCityName = itemView.findViewById<TextView>(R.id.fav_movie_name_tv)
        private val imvCityImage= itemView.findViewById<ImageView>(R.id.fav_movie_img)

        fun setData(movie: Movie, position: Int) {

        //Log.d("mmm",""+movie.title)
        //Log.d("ttt",""+ txvCityName.text)
            txvCityName.text = movie.title
            Glide.with(imvCityImage).load(IMAGE_BASE + movie.poster).into(itemView.fav_movie_img)

            this.currentPosition = position
            this.currentCity = movie

        }

     }
//    fun setDBData(movie: List<com.example.movieretrokot.Data.Movie>){
//        this.mList=movie
//        notifyDataSetChanged()
//    }
}
