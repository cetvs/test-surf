package com.example.myapplication.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository
    var readAll: LiveData<List<Movie>>

    init {
        val noteDao = AppDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(noteDao)
        readAll = repository.getAll()
    }

    fun addMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovie(movie)
        }
    }

    fun addMovies(lst: List<Movie>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovies(lst)
        }
    }

    fun getMovie(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMovie()
            repository.addMovie(response)
        }
    }

    fun deleteMovies(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMovies()
        }
    }

    fun searchForItems(searchQuery: String) : LiveData<List<Movie>> {
        return repository.search(searchQuery)
    }

}