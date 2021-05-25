package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MoviesList : AppCompatActivity() {
    private val movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
   override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movies_list)

       val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
       moviesAdapter = MoviesAdapter{ view -> adapterOnClick(view) }
       val layoutManager = LinearLayoutManager(applicationContext)
       recyclerView.layoutManager = layoutManager
       recyclerView.itemAnimator = DefaultItemAnimator()
       recyclerView.adapter = moviesAdapter

       val actionbar = supportActionBar
       "Movies List".also { actionbar!!.title = it }
       val displayHomeAsUpEnabled = actionbar?.setDisplayHomeAsUpEnabled(true)


   }

    private fun adapterOnClick(view: Int) {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


       }
















    }