package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Movie
import com.example.myapplication.room.MovieViewModel
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(), SearchView.OnQueryTextListener, MyRecyclerAdapter.OnItemClickListener {
    //private lateinit var binding: ActivityMainBinding

    //private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var myAdapter: MyRecyclerAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        myAdapter = MyRecyclerAdapter(mutableListOf(),this)
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu,menu)
        val search = menu.findItem(R.id.main_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View?
    {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.deleteMovies()

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val r = rv_movie.apply {
            layoutManager = LinearLayoutManager(activity)
            movieViewModel.readAll.observe(viewLifecycleOwner, Observer {
                myAdapter.setData(it)
                adapter = myAdapter
            })
        }

        movieViewModel.addMovies(setExampleShare())
    }

    fun setExampleShare() : ArrayList<Movie>
    {
        var id: Int = 0
        val lst = ArrayList<Movie>()
        lst.add(
            Movie(1,"Blade runner 2049",
            "Отставной детектив Рик Декард вновь восстановлен в полиции Лос-Анджелеса ", R.drawable.blade, false)
        )
        lst.add(
            Movie(2,"Arrhythmia 18+",
                "Олег - талантливый врач, работает на «скорой», которая мчится от пациента ", R.drawable.com,true)
        )
        lst.add(
                Movie(3,"Ghost in the shell",
                        "Ghost in the shell (test)", R.drawable.com,true)
        )
        return lst
    }


    override fun onStart() {
        super.onStart()
        first_btn_next.setOnClickListener{
            (activity as MainActivity).navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }

        first_btn_back.setOnClickListener{
            movieViewModel.getMovie()
        }
    }

    private fun getItemsFromDb(query: String) {

        var searchText = "%$query%"

        movieViewModel.searchForItems(searchQuery = searchText).observe(this@FirstFragment, Observer { list ->
//            myAdapter.setData(list)
            list.let {
                myAdapter.setData(it)
            }
        })

    }

    override fun onQueryTextSubmit(newText: String?): Boolean {
        if (newText != null) {
            getItemsFromDb(newText)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            getItemsFromDb(newText)
        }
        return true
    }

    override fun onItemClick(position: Int) {
        Log.d("3","3")
        var str : String? = ""
        movieViewModel.readAll.observe(viewLifecycleOwner, Observer {
            str = it[position].name
        })
        Toast.makeText(activity, "$str", Toast.LENGTH_SHORT).show()
    }
}