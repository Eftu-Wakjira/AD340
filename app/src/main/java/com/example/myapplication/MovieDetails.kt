package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.example.myapplication.MoviesAdapter
class MovieDetails: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedetails)

        // to display the clicked movie title text:
        //val b = this.intent.extras
        val message = intent.extras?.getStringArray("details")

        //started working on a default image, but didn't complete that
        val movieimage = findViewById<ImageView>(R.id.movieimage)

        val title = findViewById<TextView>(R.id.title)
        val year= findViewById<TextView>(R.id.year)
        val actor = findViewById<TextView>(R.id.actor)
        val description = findViewById<TextView>(R.id.description)


       title.text = message?.get(0)
        year.text = message?.get(1)
        actor.text = message?.get(2)
       Picasso.get().load(message?.get(3)).into(movieimage)
        description.text = message?.get(4)

    }
}
