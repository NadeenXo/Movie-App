package com.example.movieretrokot.Repository

import androidx.lifecycle.LiveData
import com.example.movieretrokot.Movie.Movie


interface IRepository <T>{
//     fun getAll(): LiveData<List<T>>
    suspend fun insertAllMovies(value:List<T>?)
    suspend fun loadAllMovies():List<T>
     fun getFavMovies():LiveData<List<T>>
}