package ir.simyapp.batmanmoviesapp.data.local

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import ir.simyapp.batmanmoviesapp.models.Movie

/**
 * @author Sahar Simyari
 */

/*@Database(
    exportSchema = true,
    version = 2,
    entities = [Movie::class],
    autoMigrations = [
        AutoMigration(from = 1,to = 2)
    ]
)*/

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase :RoomDatabase(){

    abstract fun MovieDao():MovieDao
}