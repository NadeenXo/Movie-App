package com.example.movieretrokot.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.movieretrokot.Movie.Movie

@Dao
interface AppDao {
    // read data
    @Query("SELECT * FROM Movie_table WHERE NOT EXISTS(select title FROM Movie_table) ORDER BY title ASC")
     fun getAll(): LiveData<List<Movie>>

     // insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie:Movie)



    @Query("SELECT * FROM Movie_table WHERE isFavorite = 'true' ")
     fun readFav(): LiveData<List<Movie>>

    @Delete
     fun delete(movie:Movie)
     @Insert
    abstract fun insertMovies(movies: List<Movie>?)
}