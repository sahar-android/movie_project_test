package ir.simyapp.batmanmoviesapp.data.local

import androidx.room.*
import ir.simyapp.batmanmoviesapp.models.Movie

/**
 * @author Sahar Simyari
 */

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAll():List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM Movie")
    fun deleteAll()
}