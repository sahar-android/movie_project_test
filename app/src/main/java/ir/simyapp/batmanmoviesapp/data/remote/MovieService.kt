package ir.simyapp.batmanmoviesapp.data.remote

import ir.simyapp.batmanmoviesapp.models.BatmanMovies
import ir.simyapp.batmanmoviesapp.models.MovieDetail
import ir.simyapp.batmanmoviesapp.models.ReciveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Sahar Simyari
 */
interface MovieService {
    @GET("?apikey=3e974fca&s=batman")
    suspend fun getBatmanMovies():Response<ReciveData>

    @GET("?apikey=3e974fca")
    suspend fun getDetailMovie(@Query("i") id:String): Response<MovieDetail>


}