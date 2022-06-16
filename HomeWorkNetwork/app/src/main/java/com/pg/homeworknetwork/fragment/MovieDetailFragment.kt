package com.pg.homeworknetwork.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pg.homeworknetwork.BuildConfig
import com.pg.homeworknetwork.R
import com.pg.homeworknetwork.model.Movie
import com.pg.homeworknetwork.repo.TmdbRepo
import kotlinx.coroutines.*

class MovieDetailFragment : Fragment(R.layout.fragment_movie_preview) {
    lateinit var poster: ImageView
    private lateinit var originalTitle: TextView
    lateinit var overview: TextView
    private lateinit var popularity: TextView
    private lateinit var releaseDate: TextView

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            poster = findViewById(R.id.poster)
            originalTitle = findViewById(R.id.originalTitle)
            overview = findViewById(R.id.overview)
            popularity = findViewById(R.id.popularity)
            releaseDate = findViewById(R.id.releaseDate)
        }

        val movieId = arguments?.getInt(ARG_ID) ?: 550

        scope.launch {
            TmdbRepo.getMovie(movieId)?.let {
                poster.load("${BuildConfig.API_IMAGE_BASE_URL}${it.posterPath}") {
                    transformations(RoundedCornersTransformation(16f))
                }
                originalTitle.text = it.originalTitle
                overview.text = it.overview
                popularity.text = it.popularity.toString()
                releaseDate.text = it.releaseDate
            }
        }

        activity?.onBackPressedDispatcher?.addCallback(
            this.viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val manager: FragmentManager = parentFragmentManager
                    val transaction: FragmentTransaction = manager.beginTransaction()
                    transaction.replace(R.id.mainFragment, MovieListFragment())
                    transaction.commit()
                }
            })
    }

    companion object {
        const val TAG = "MovieDetailFragment"
        const val ARG_ID = "MovieDetailFragment_Arguments_Movie_Id"
    }
}