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

//    val readAll: LiveData<List<Movie>>

    private val repository: MovieRepository

    init {
        favMovieList.value = mutableListOf()
        //movieList.value?.let { setupMovies(it) }

        val mDao=  AppDB.getDB(application).movieDao()
        repository= MovieRepository(mDao)
//        readAll=repository.getAll()

    }


    fun setupMovies(movies:List<Movie>) {
//        movieList.value=movies

        viewModelScope.launch(Dispatchers.IO)
        {

            movieList.postValue(repository.loadAllMovies())
            Log.d(" MVM ", " loadAllMovies")

        }

        // insert data
        //run one time
//        viewModelScope.launch(Dispatchers.IO)
//        {
//            insertAllMovies(movies)
//        }

    }


    fun addFavMovie(mv: Movie) {
        for (m in movieList.value!!){
            if (m.id==mv.id) {
                favMovieList.value?.add(mv)
                m.isFavorite=true
            }
            }

        Log.d("MVM", "addFavMovie: added movie $mv")
    }


    fun deleteFavMovie(mv: Movie) {
        for (m in movieList.value!!){
            if (m.id==mv.id) {
                favMovieList.value?.remove(mv)
                m.isFavorite=false
            }
        }
        Log.d("MVM", "deleteFavMovie:remove movie $mv")

    }

    suspend fun insertAllMovies(Movies: List<Movie>?) {
    repository.insertAllMovies(Movies)
        Log.d("MVM", "insert Movies to DB: $Movies")

    }


//    fun updateMovie(movie: Movie) {
//
//        for (m in movieList.value!!){
//            if (m.id==movie.id){
//                m.isFavorite=true
//            }
//        }
//    }


}