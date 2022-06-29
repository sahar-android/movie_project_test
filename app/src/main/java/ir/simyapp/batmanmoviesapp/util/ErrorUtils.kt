package ir.simyapp.batmanmoviesapp.util

import ir.simyapp.batmanmoviesapp.models.ErrorGetMovie
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


/**
 * @author Sahar Simyari
 */
object ErrorUtils {
    fun parseError(response: Response<*>, retrofit: Retrofit): ErrorGetMovie? {
      val converter = retrofit.responseBodyConverter<ErrorGetMovie>(ErrorGetMovie::class.java, arrayOfNulls(0))

       return try {
           converter.convert(response.errorBody()!!)
       }catch (e:IOException){
           ErrorGetMovie()
       }
    }
}