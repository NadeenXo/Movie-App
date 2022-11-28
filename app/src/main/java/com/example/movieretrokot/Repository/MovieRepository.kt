package com.example.movieretrokot.Repository

import androidx.lifecycle.LiveData
import com.example.movieretrokot.Data.AppDao
import com.example.movieretrokot.Movie.Movie

class MovieRepository(private val movieDao: AppDao) : IRepository<Movie> {
//  val readAll: LiveData<List<Movie>> = movieDao.getAll()

//    override fun getAll(): LiveData<List<Movie>> {
//        return movieDao.getAll()
//    }

    override suspend fun insertAllMovies(movies: List<Movie>?) {
        movieDao.insertMovies(movies)
    }

    override suspend fun loadAllMovies():List<Movie> {
        return movieDao.loadAllMovies()
    }

    override fun getFavMovies(): LiveData<List<Movie>> {
        return movieDao.readFav()
    }



}