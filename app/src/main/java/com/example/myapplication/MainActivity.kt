package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.data.Movie
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.MovieDao
import com.example.myapplication.room.MovieRepository
import com.example.myapplication.room.MovieViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity()//, SearchView.OnQueryTextListener  {
{
    lateinit var navController: NavController
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host)
    }
}