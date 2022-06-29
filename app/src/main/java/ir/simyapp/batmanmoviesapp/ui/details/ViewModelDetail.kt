package ir.simyapp.batmanmoviesapp.ui.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import ir.simyapp.batmanmoviesapp.data.remote.MovieRepository
import ir.simyapp.batmanmoviesapp.models.MovieDetail
import ir.simyapp.batmanmoviesapp.models.ReciveData
import ir.simyapp.batmanmoviesapp.models.ResultMovies
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Sahar Simyari
 */
@HiltViewModel
class ViewModelDetail @Inject constructor(private  val movieRepository: MovieRepository) : ViewModel() {

    private var _id=MutableLiveData<String>()
    private val _movieDetail: LiveData<ResultMovies<MovieDetail>> = _id.distinctUntilChanged().switchMap {
        liveData {
            movieRepository.fetchDetailMovie(it).onStart {
                emit(ResultMovies.loading())
            }.collect {
                emit(it)
            }
        }
    }

    val movieDtail=_movieDetail


    fun fetchingDtailMovie(id:String){
        _id.value=id
    }

}