package com.example.movieretrokot.Repository

import androidx.lifecycle.LiveData
import com.example.movieretrokot.Data.AppDao
import com.example.movieretrokot.Movie.Movie

class MovieRepository(private val movieDao: AppDao) : IRepository<Movie> {
  val readAll: LiveData<List<Movie>> = movieDao.getAll()

    override fun getAll(): LiveData<List<Movie>> {
        return movieDao.getAll()
    }

//        suspend fun addMovie(movie: Movie){
//        movieDao.addMovie(movie)
//    }
    override suspend fun insert(movie: Movie) {
        movieDao.addMovie(movie)
    }


    override fun getFavMovies(): LiveData<List<Movie>> {
        return movieDao.readFav()
    }

    fun insertAllMovies(movies: List<Movie>?) {
        movieDao.insertMovies(movies)
    }
}