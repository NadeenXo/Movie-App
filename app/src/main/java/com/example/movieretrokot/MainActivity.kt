package com.example.movieretrokot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.movieretrokot.Movie.*
import com.example.movieretrokot.Services.API_Service
import com.example.movieretrokot.Services.API
import com.example.movieretrokot.ViewModel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar        : Toolbar
    private lateinit var navController  : NavController
    private lateinit var bottomNavView  : BottomNavigationView

    val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        toolbar         = findViewById(R.id.activity_main_toolbar)
        bottomNavView   = findViewById(R.id.bottom_nav_view)

        // Get NavHostFragment and NavController
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController   = navHostFrag.navController

        // Define AppBarConfiguration
        val topLevelDestinations = setOf(R.id.fragmentMovieList, R.id.fragmentFavoriteList)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)

//        getMovieData { movies : List<Movie> ->
//            MovieAdapter(MovieAdapter::class.java,viewModel)
//        }
        // Connect Toolbar with NavController
        toolbar.setupWithNavController(navController, appBarConfiguration)

        // Connect BottomNavigationView with NavController
        bottomNavView.setupWithNavController(navController)


        getMovieData {
            viewModel.setupMovies(it)
        }

    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = API_Service.getInstance().create(API::class.java)
        apiService.getData().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }

            override fun onResponse(
                call: Call<MovieResponse>,
               response: retrofit2.Response<MovieResponse>
            ) {
                return callback(response.body()!!.movies)
            }

        })

    }


}