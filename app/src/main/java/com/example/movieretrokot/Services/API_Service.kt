package com.example.movieretrokot.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_Service {
    companion object{
        const val BASE_API_URL = "https://api.themoviedb.org/"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    }
}
