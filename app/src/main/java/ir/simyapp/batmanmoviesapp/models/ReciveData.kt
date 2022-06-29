package ir.simyapp.batmanmoviesapp.models

import com.google.gson.annotations.SerializedName

/**
 * @author Sahar Simyari
 */
data class ReciveData(
    @SerializedName("Search")
    val search:List<Movie>,
    val totalResults:String,
    @SerializedName("Response")
    val response:String

)

