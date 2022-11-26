package com.example.movieretrokot.Repository

import androidx.lifecycle.LiveData


interface IRepository <T>{
     fun getAll(): LiveData<List<T>>
    suspend fun insert(value:T)
     fun getFavMovies():LiveData<List<T>>
}