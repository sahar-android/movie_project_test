package ir.simyapp.batmanmoviesapp.ui.details

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ir.simyapp.batmanmoviesapp.R
import ir.simyapp.batmanmoviesapp.databinding.ActivityDetailMovieBinding
import ir.simyapp.batmanmoviesapp.models.MovieDetail
import ir.simyapp.batmanmoviesapp.models.ResultMovies
import ir.simyapp.batmanmoviesapp.ui.AppNameGlideModule


@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModelDetail by viewModels<ViewModelDetail>()
    private lateinit var picasso: Picasso


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        picasso= Picasso.get()

        intent?.getStringExtra(MOVIE_ID)?.let {
            viewModelDetail.fetchingDtailMovie(it)
            subscribeDetailUi()
        } ?:showError("not detail exist")
    }

    private fun subscribeDetailUi() {

            viewModelDetail.movieDtail.observe(this, Observer { result ->
                Log.i("gjhgj", "subscribeDetailUi: "+result.toString())
                when(result.status){
                    ResultMovies.Status.SUCCESS -> {
                      result.data?.let {
                          updateUi(it)
                          Log.i("gjhgj", "success: "+result.data.toString())
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

    private fun updateUi(movieDetail:MovieDetail){
        title="Batman's movies"
        binding.titleMovie.text=movieDetail.title
        binding.release.text=movieDetail.released
        binding.movieGenre.text=movieDetail.genre
        binding.movieLanguage.text=movieDetail.language
        binding.movieCountry.text=movieDetail.country
        binding.moviePlot.text=movieDetail.plot
        binding.movieActors.text=movieDetail.actors
        binding.movieDirector.text=movieDetail.director
        binding.imdbID.text=movieDetail.imdbID
        binding.imdbRating.text=movieDetail.imdbRating
        binding.imdbVotes.text=movieDetail.imdbVotes


        Glide.with(this).load(movieDetail.poster)
            .centerInside().placeholder(R.drawable.batman)
            .into(binding.moviePoster)
    }

    private fun showError(msg: String) {
        Snackbar.make(this, binding.constrain, msg, Snackbar.LENGTH_INDEFINITE)
            .setAction("Dissmiss") {
            }.show()

        Log.i("gtfhy", "showError: "+msg)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object{
        const val MOVIE_ID="movie_id"
    }
}