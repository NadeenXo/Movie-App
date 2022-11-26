package com.example.movieretrokot.Favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.movieretrokot.R
import com.example.movieretrokot.Movie.Movie
import com.example.movieretrokot.ViewModel.MovieViewModel
import java.util.*

class FavoriteFragment : Fragment() {

    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var recyclerView: RecyclerView


    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("favFrag", "oncreate")

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        setupView(view)

        return view
    }

    private fun setupView(view: View) {

        val context = requireContext()

//       favMovieList = viewModel.favMovieList as ArrayList<Movie>

        favoriteAdapter = FavoriteAdapter(context)

        viewModel.favMovieList.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }

       // viewModel.readAll.observe(viewLifecycleOwner, androidx.lifecycle.Observer { m->favoriteAdapter.setDBData(m)})


        recyclerView = view.findViewById(R.id.favorite_recycler_view)
        recyclerView.adapter = favoriteAdapter
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT
    ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            targetViewHolder: RecyclerView.ViewHolder
        ): Boolean {
            // Called when the item is dragged.
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(viewModel.favMovieList.value!!, fromPosition, toPosition)

            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // Called when the item is swiped.
            val position = viewHolder.adapterPosition
            val deletedMovie: Movie = viewModel.favMovieList.value!![position]

            deleteItem(position)
            updateList(deletedMovie, false)

            Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDelete(position, deletedMovie)
                    updateList(deletedMovie, true)
                }
                .show()
        }
    })

    private fun deleteItem(position: Int) {
        viewModel.favMovieList.value!!.removeAt(position)
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeChanged(position, viewModel.favMovieList.value!!.size)

    }

    private fun updateList(deletedMovie: Movie, isFavorite: Boolean) {
        deletedMovie.isFavorite = isFavorite
    }

    private fun undoDelete(position: Int, deletedMovie: Movie) {
        viewModel.favMovieList.value!!.add(position, deletedMovie)
        favoriteAdapter.notifyItemInserted(position)
        favoriteAdapter.notifyItemRangeChanged(position, viewModel.favMovieList.value!!.size)
    }
}
