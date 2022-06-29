package ir.simyapp.batmanmoviesapp.models

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Sahar Simyari
 */
@Entity
data class Movie(
    @Nullable
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("Title")
    val title:String?,
    @SerializedName("Year")
    val year:String?,
    @SerializedName("imdbID")
    val imdbID:String?,
    @SerializedName("Type")
    val type:String?,
    @SerializedName("Poster")
    val poster:String?
)
