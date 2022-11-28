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
        val adapter = MovieAdapter(context, viewModel)

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.submitList(it)

        }

        movie_recyview.adapter = adapter


        return view
    }

}