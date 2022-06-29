package ir.simyapp.batmanmoviesapp.data.remote

import android.util.Log
import ir.simyapp.batmanmoviesapp.models.BatmanMovies
import ir.simyapp.batmanmoviesapp.models.MovieDetail
import ir.simyapp.batmanmoviesapp.models.ReciveData
import ir.simyapp.batmanmoviesapp.models.ResultMovies
import ir.simyapp.batmanmoviesapp.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

/**
 * @author Sahar Simyari
 */
class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchBatmanMovies():ResultMovies<ReciveData>{
        val movieService=retrofit.create(MovieService::class.java)
        /*val movieArray=movieService.getBatmanMovies().body()?.search
        Log.i("ghgnhg", "fetchBatmanMovies: "+movieArray.toString())*/
        return getResponse(
            request = { movieService.getBatmanMovies()},
            defaultErrorMessage = "error in get Batman movies"
        )

    }

    suspend fun fetchDetailMovie(id:String):ResultMovies<MovieDetail>{
        val movieService=retrofit.create(MovieService::class.java)
        return getResponse(
            request = {movieService.getDetailMovie(id)},
            defaultErrorMessage = "error in get detail of movie"
        )
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage:String):ResultMovies<T>{

        return try {
            val result=request.invoke()
            if(result.isSuccessful){

                Log.i("ghgnhg", "getResponse: "+result.body().toString())
                return ResultMovies.success(result.body())

            }else{
                val errorResponse=ErrorUtils.parseError(result,retrofit)
                ResultMovies.error(errorResponse?.Status_message ?: defaultErrorMessage, errorResponse)


            }
        }catch (e:Throwable){
            ResultMovies.error(e.message.toString(),null)

        }

    }
}