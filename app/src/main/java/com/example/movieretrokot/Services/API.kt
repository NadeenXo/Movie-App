package com.example.movieretrokot.Services

import com.example.movieretrokot.Movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface API {
//    https://api.themoviedb.org/3/movie/popular?api_key=f63a0505fbfdc320544c7520184eae43&language=en-US&page=1
    @GET("3/movie/popular?api_key=f63a0505fbfdc320544c7520184eae43")
    fun getData():Call<MovieResponse>


}