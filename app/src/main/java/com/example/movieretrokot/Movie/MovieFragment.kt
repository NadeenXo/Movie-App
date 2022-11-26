package com.example.movieretrokot.Movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.example.movieretrokot.R
import com.example.movieretrokot.ViewModel.MovieViewModel

class MovieFragment : Fragment() {
    private lateinit var movie_recyview: RecyclerView

//    private val viewModel by lazy {
//        ViewModelProviders.of(this)[MovieViewModel::class.java]
//    }

    val viewModel: MovieViewModel by activityViewModels()

//    lateinit var viewModel:MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("movieFrag", "onCreateView")
        val context = requireContext()
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        movie_recyview = view.findViewById(R.id.movie_recyview)
        movie_recyview.layoutManager = LinearLayoutManager(context)
        movie_recyview.setHasFixedSize(true)

//          viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

//        val adapter = MovieAdapter( context)
        val adapter = MovieAdapter( context , viewModel)

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
//            viewModel.insertAllMovies(it)
        }

        movie_recyview.adapter = adapter

        //read from room
//        viewModel.readAll.observe(viewLifecycleOwner) { m -> adapter.setData(m) }

        return view
    }

    @Synchronized
    fun insertDB(vote: String, title: String, img: String, release: String, isf: Boolean) {
        synchronized(this) {
            //create obj
            val m = Movie(0, vote, title, img, release, isf)

            //add to db
            viewModel.addMovie(m)
            Log.d("DB", "$m")
            Toast.makeText(requireContext(), "$title  added", Toast.LENGTH_LONG).show()

        }
    }
}

//    }
//    @Synchronized
//    fun insertDB( isf:Boolean){
//        val t = movie_name_tv.text?.toString()
//        val r = movie_rate_tv.text?.toString()
//        val d = movie_date_tv.text?.toString()
//        //val f = fav_icon_img.toString()
//        val m = movie_img?.toString()
//        if (check(t!!, m!!)) {
//            //create obj
//            val m = Movie(0, r, t, m, d, isf)
//
//            //add to db
//            viewModel.addMovie(m)
//            Log.d("DB", "$m")
//            Toast.makeText(requireContext(),"added", Toast.LENGTH_LONG).show()
//        }
//
//    }
//    private fun check(t:String,img:String):Boolean{
//        return !(TextUtils.isEmpty(t) && TextUtils.isEmpty(img))
//    }

//}