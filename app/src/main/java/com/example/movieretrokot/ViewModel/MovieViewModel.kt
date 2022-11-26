package com.example.movieretrokot.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.movieretrokot.Data.AppDB
import com.example.movieretrokot.Movie.Movie
import com.example.movieretrokot.Repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    var movieList: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    var favMovieList: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()

    val readAll: LiveData<List<Movie>>

    private val repository: MovieRepository

    init {
        favMovieList.value = mutableListOf()
        //movieList.value?.let { setupMovies(it) }

        val mDao=  AppDB.getDB(application).movieDao()
        repository= MovieRepository(mDao)
        readAll=repository.getAll()
    }

    fun addMovie(movie: Movie) {
        //run in background
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insert(movie)
        }
    }

    fun setupMovies(movies:List<Movie>) {
        movieList.value = movies

//        repository.insertAllMovies(movies)

    }

//    fun setupFavMovies(lm: MutableList<Movie>) {
//        favMovieList.value = lm
//    }

    fun addFavMovie(mv: Movie) {
        favMovieList.value?.add(mv)
        Log.d("TAG", "addFavMovie: added movie $mv")
    }


    fun deleteFavMovie(mv: Movie) {
        favMovieList.value?.remove(mv)
        Log.d("TAG", "addFavMovie:re movie $mv")

    }

    fun insertAllMovies(Movies: List<Movie>?) {
    repository.insertAllMovies(Movies)
    }

//    fun addFavMovie2(mv: com.example.movieretrokot.Data.Movie) {
//        mv.isFavorite=true
//        Log.d("TAG", "addFavMovie: added movie $mv")
//    }
//    fun deleteFavMovie2(mv: com.example.movieretrokot.Data.Movie) {
//        mv.isFavorite=false
//        Log.d("TAG", "addFavMovie:re movie $mv")
//
//    }


}