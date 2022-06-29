package ir.simyapp.batmanmoviesapp.data.remote

import android.util.Log
import com.google.gson.JsonObject
import ir.simyapp.batmanmoviesapp.data.local.MovieDao
import ir.simyapp.batmanmoviesapp.models.MovieDetail
import ir.simyapp.batmanmoviesapp.models.ReciveData
import ir.simyapp.batmanmoviesapp.models.ResultMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject


/**
 * @author Sahar Simyari
 */


class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource, private val movieDao: MovieDao
) {

    suspend fun fetchBatmanMovieList(): Flow<ResultMovies<ReciveData>?> {
        return flow {
            emit(ResultMovies.loading())
            emit(fetchBatmanMoviesCached())
            val result = movieRemoteDataSource.fetchBatmanMovies()
            if (result.status == ResultMovies.Status.SUCCESS) {

                 result.data?.search.let {
                     if (it != null) {
                        movieDao.deleteAll()
                        movieDao.insertAll(it)

                    }
                }
            }

            emit(result)

        }.flowOn(Dispatchers.IO)
    }


    private fun fetchBatmanMoviesCached(): ResultMovies<ReciveData> {
        val savedData=movieDao.getAll()
       val reciveDataModel=ReciveData(savedData,"","")
        return reciveDataModel.let {
            ResultMovies.success(it)
        }

    }


    suspend fun fetchDetailMovie(id: String): Flow<ResultMovies<MovieDetail>> {
        return flow {
            emit(ResultMovies.loading())
            emit(movieRemoteDataSource.fetchDetailMovie(id))
            Log.i("gjhgj", "fetchDetailMovie: "+movieRemoteDataSource.fetchDetailMovie(id).toString())
        }.flowOn(Dispatchers.IO)
    }

}
