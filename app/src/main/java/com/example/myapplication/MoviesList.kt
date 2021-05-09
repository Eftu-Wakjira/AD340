package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesList : AppCompatActivity() {
    private val movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
   override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movies_list)

       val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
       moviesAdapter = MoviesAdapter(movieList)
       val layoutManager = LinearLayoutManager(applicationContext)
       recyclerView.layoutManager = layoutManager
       recyclerView.itemAnimator = DefaultItemAnimator()
       recyclerView.adapter = moviesAdapter


       val actionbar = supportActionBar
       actionbar!!.title = "Movies List"
       actionbar.setDisplayHomeAsUpEnabled(true)


   }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


       }
















    }