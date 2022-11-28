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


class FavoriteAdapter(private val context: Context):
    ListAdapter<Movie,FavoriteAdapter.FavViewHolder>(MovieItemCallback) {

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
        Log.d("FavAdapter", "info $movie")
        itemViewHolder.setData(movie, position)
    }

    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        private var currentPosition: Int = -1
        private var currentMovie: Movie?   = null

        private val txtMovieName = itemView.findViewById<TextView>(R.id.fav_movie_name_tv)
        private val imvMovieImage= itemView.findViewById<ImageView>(R.id.fav_movie_img)

        fun setData(movie: Movie, position: Int) {

            txtMovieName.text = movie.title
            Glide.with(imvMovieImage).load(IMAGE_BASE + movie.poster)
                                     .into(itemView.fav_movie_img)

            this.currentPosition = position
            this.currentMovie = movie

        }

     }

}
