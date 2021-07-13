package com.example.myapplication.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.data.Movie
import retrofit2.Retrofit

class MovieRepository(private val movieDao: MovieDao) {

    val allWords: LiveData<List<Movie>> = movieDao.getAll()

    suspend fun addMovie(el: Movie){
        movieDao.insertMovie(el)
    }

    suspend fun addMovies(lst: List<Movie>){
        for (elem in lst)
            movieDao.insertMovie(elem);
    }

    suspend fun getMovie(): Movie{
        return RetrofitInstance.api.getMovie()
    }

    fun deleteMovies() {
        return movieDao.deleteAll()
    }

    fun getAll(): LiveData<List<Movie>>{
        return movieDao.getAll()
    }

    //@WorkerThread
    fun search(searchQuery : String) : LiveData<List<Movie>>{
        return movieDao.getSearchResults(searchQuery)
    }




}