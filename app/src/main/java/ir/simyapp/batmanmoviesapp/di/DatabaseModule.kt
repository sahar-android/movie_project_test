package ir.simyapp.batmanmoviesapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.simyapp.batmanmoviesapp.data.local.AppDatabase
import ir.simyapp.batmanmoviesapp.data.local.MovieDao
import javax.inject.Singleton

/**
 * @author Sahar Simyari
 */

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext:Context):AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "myApp.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase):MovieDao{
        return appDatabase.MovieDao()
    }

}