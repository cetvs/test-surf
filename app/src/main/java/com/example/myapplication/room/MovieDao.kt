package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE movie.name LIKE  :searchQuery")
    fun getSearchResults(searchQuery : String) : LiveData<List<Movie>>

    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<List<Movie>>

    @Insert
    fun insertMovie(movie: Movie)
//
//    @Insert
//    fun insertAll(vararg movie: Movie)

    @Query("DELETE FROM movie")
    fun deleteAll()

//    @Delete
//    fun delete(user: Movie)
}