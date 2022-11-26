package com.example.movieretrokot.Movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieretrokot.R
import com.example.movieretrokot.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieAdapter(
    val context: Context, val vm: MovieViewModel
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieItemCallback) {

    companion object MovieItemCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    private var movieList = emptyList<Movie>()

    inner class MovieViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        private var currentPosition: Int = -1
        private lateinit var currentMovie: Movie

//        private val txvMovieName = itemView.findViewById<TextView>(R.id.movie_name_tv)
//        private val imvMovieImage= itemView.findViewById<ImageView>(R.id.movie_img)
        private val imvFavorite = itemView.findViewById<ImageView>(R.id.fav_icon_img)


        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_baseline_favorite_24, null
        )
        private val icFavoriteBorderedImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_baseline_favorite_border_24, null
        )


        fun bindMovie(movie: Movie, position: Int) {

//            txvMovieName.text = movie.title
            itemView.movie_name_tv.text = movie.title
            itemView.movie_date_tv.text = movie.release
            itemView.movie_rate_tv.text = movie.vote
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_img)
//            Glide.with(itemView).load(movie.poster).into(itemView.movie_img)

            if (movie.isFavorite) {
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
            } else {
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)

            }

            this.currentPosition = position
            this.currentMovie = movie


//            context.insertDB(movie.title!!, movie.vote!!,
//                                movie.poster!!,movie.release!!,
//                                currentMovie!!.isFavorite)
        }

        fun setListeners() {
//            imvDelete.setOnClickListener(this@CityViewHolder)
            imvFavorite.setOnClickListener(this@MovieViewHolder)
//            imvFavorite.setOnClickListener{
//                vm.updateMovie(currentMovie.id,!currentMovie.isFavorite)
//            }
        }

        override fun onClick(v: View?) {
            when (v!!.id) {
                R.id.fav_icon_img -> addToFavorite()
//                R.id.delete_icon_img -> deleteItem()
            }
        }


        fun addToFavorite() {
            currentMovie?.isFavorite =
                !(currentMovie.isFavorite)        // Toggle the 'isFavourite' Boolean value

            if (currentMovie?.isFavorite!!) {        // if it is favorite - update icon and add the object to favorite list
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
//                vm.addFavMovie2(currentMovie!!)
                vm.addFavMovie(currentMovie!!)

//                FavMovie.favoriteMovieList.add(currentMovie!!)
//

            } else {        // else it is not favorite - update icon and remove the object from favorite list
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)
//                vm.deleteFavMovie2(currentMovie!!)

                vm.deleteFavMovie(currentMovie!!)
//                FavMovie.favoriteMovieList.remove(currentMovie!!)
            }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.setListeners()
        holder.bindMovie(movie, position)

    }
    fun setData(movie: List<Movie>) {
        movieList=movie
        notifyDataSetChanged()

    }

}