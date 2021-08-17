package com.backbase.assignment.ui.movie

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.utils.getDateString
import com.backbase.assignment.viewmodel.MovieDetailsViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : DialogFragment(R.layout.fragment_movie_details){
    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var overview: TextView
    private lateinit var titleView: TextView
    private lateinit var releaseDateView: TextView
    private lateinit var posterImage: ImageView
    private lateinit var back: ImageView
    private lateinit var loader: ProgressBar
    private lateinit var parent: ConstraintLayout
    private val movieViewModel: MovieDetailsViewModel by viewModels()
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogFragmentWidthInPercentage(95)

        genreRecyclerView = view.findViewById(R.id.genreList)
        overview = view.findViewById(R.id.overview)
        titleView = view.findViewById(R.id.movieTitle)
        releaseDateView = view.findViewById(R.id.movieReleaseDate)
        posterImage = view.findViewById(R.id.poster)
        back = view.findViewById(R.id.back)
        loader = view.findViewById(R.id.progressBar)
        parent = view.findViewById(R.id.rootView)

        back.setOnClickListener {
            findNavController().navigateUp()
        }

        genreRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        movieViewModel.getMovieDetais(args.id)


        movieViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                if (isLoading)
                    loader.visibility = View.VISIBLE
                else
                    loader.visibility = View.GONE
            }
        })
        movieViewModel.movieDetailsPage.observe(viewLifecycleOwner, Observer {
            overview.text = it?.overview
            titleView.text = it?.title
            releaseDateView.text = it?.releaseDate?.getDateString()
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original${it?.posterPath}")
                .into(posterImage)
            genreRecyclerView.adapter = GenreAdapter(it?.genres)
        })

        movieViewModel.apiError.observe(viewLifecycleOwner, Observer {
            Snackbar.make(parent, it, Snackbar.LENGTH_LONG).show()
        })
    }

    private fun DialogFragment.setDialogFragmentWidthInPercentage(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
    }
}