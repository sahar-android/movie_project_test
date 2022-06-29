package ir.simyapp.batmanmoviesapp.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import ir.simyapp.batmanmoviesapp.data.remote.MovieRepository
import ir.simyapp.batmanmoviesapp.models.BatmanMovies
import ir.simyapp.batmanmoviesapp.models.ReciveData
import ir.simyapp.batmanmoviesapp.models.ResultMovies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Sahar Simyari
 */
@HiltViewModel
class ViewModelList @Inject constructor(private  val movieRepository: MovieRepository) : ViewModel()  {

    private val _movieList=MutableLiveData<ResultMovies<ReciveData>>()
    val movieList=_movieList

    init {
        fetchingMovies()
    }

    private fun fetchingMovies()=
        viewModelScope.launch {
            //delay(1000)
            movieRepository.fetchBatmanMovieList().collect{
                if(it?.data!=null){
                    _movieList.value=it
                }


            }
        }

}