package ir.simyapp.batmanmoviesapp.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.simyapp.batmanmoviesapp.R
import ir.simyapp.batmanmoviesapp.databinding.ActivityListMoviesBinding
import ir.simyapp.batmanmoviesapp.models.Movie
import ir.simyapp.batmanmoviesapp.models.ResultMovies
import kotlinx.coroutines.*
import java.lang.invoke.ConstantCallSite


@AndroidEntryPoint
class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding
    private val movieList = ArrayList<Movie>()
    private val viewModelList by viewModels<ViewModelList>()
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_list_movies)
        setContentView(binding.root)

        init()
        subscribeUi()


    }

    private fun init() {
        title = "Batman's movies"
        //viewModelList=ViewModelProvider(this).get(ViewModelList::class.java)


        val layoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = layoutManager

        val dividerItemDecoration =
            DividerItemDecoration(binding.rvMovies.context, layoutManager.orientation)

        binding.rvMovies.addItemDecoration(dividerItemDecoration)
        movieListAdapter = MovieListAdapter(this, movieList)
        binding.rvMovies.adapter = movieListAdapter
    }

    private fun subscribeUi() {
        viewModelList.movieList.observe(this, Observer { result ->

            when (result.status) {
                ResultMovies.Status.SUCCESS -> {
                    result.data?.search?.let { list ->
                        movieListAdapter.updateData(list)

                    }
                    binding.loading.visibility = View.GONE
                }

                ResultMovies.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    binding.loading.visibility = View.GONE
                }

                ResultMovies.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun showError(msg: String) {
        Snackbar.make(this, binding.mainConstrin, msg, Snackbar.LENGTH_INDEFINITE)
            .setAction("Dissmiss") {
            }.show()
    }
}