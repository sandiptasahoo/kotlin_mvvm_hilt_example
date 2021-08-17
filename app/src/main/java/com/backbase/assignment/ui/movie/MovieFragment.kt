package com.backbase.assignment.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie_page),
    PopularMoviesPagerAdapter.OnItemClickListener {
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var nowPlayingRecyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var parent: ConstraintLayout
    lateinit var pagerAdapter: PopularMoviesPagerAdapter
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularRecyclerView = view.findViewById(R.id.popularMovieList)
        nowPlayingRecyclerView = view.findViewById(R.id.nowPlayingList)
        loader = view.findViewById(R.id.progressBar)
        parent = view.findViewById(R.id.rootView)

        popularRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        nowPlayingRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        movieViewModel.getNowPlayingMoviePage()

        movieViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                if (isLoading)
                    loader.visibility = View.VISIBLE
                else
                    loader.visibility = View.GONE
            }
        })
        movieViewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer {
            nowPlayingRecyclerView.adapter = NowPlayingMovieAdapter(it.results)
        })

        movieViewModel.apiError.observe(viewLifecycleOwner, Observer {
            Snackbar.make(parent, it, Snackbar.LENGTH_LONG).show()
        })

        pagerAdapter = PopularMoviesPagerAdapter(this)
        popularRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagerAdapter
        }

        lifecycleScope.launch {
            movieViewModel.getPopularMoviePage().collectLatest {
                pagerAdapter.submitData(it)
            }
        }
    }

    override fun onItemClick(id: Int?) {
        val action =
            id?.let { MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(it) }
        if (action != null) {
            findNavController().navigate(action)
        }
    }
}