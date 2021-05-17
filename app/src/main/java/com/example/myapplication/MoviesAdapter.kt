package com.example.myapplication

import android.content.ContentUris
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.ResourceManagerInternal.get
import androidx.constraintlayout.motion.utils.CurveFit.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.get
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.System.load
import java.lang.reflect.Array.get
import java.nio.file.Paths.get
import kotlin.coroutines.EmptyCoroutineContext.get

internal class MoviesAdapter :
        RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var year: TextView = view.findViewById(R.id.year)
        var actor: TextView = view.findViewById(R.id.actor)
        var description: TextView = view.findViewById(R.id.description)
        //var image: ImageView=view.findViewById(R.id.image)





    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row,
                        parent,
                        false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie[0]
        holder.year.text = movie[1]
        holder.actor.text = movie[2]
        //Picasso.get().load(movie[3]).into(holder.image)
        //holder.description.setOnClickListener { v ->
         //   Log.d(TAG, "onClick: clicked on " + movie[1])
        //    Toast.makeText( this, movie[4], Toast.LENGTH_LONG).show();
         //   val intent = Intent(v.context, MoviesList::class.java)
          //  val message: String = movie[4]
          //  intent.putExtra(AlarmClock.EXTRA_MESSAGE, message)
          //  v.context.startActivity(intent)

    }
   override fun getItemCount(): Int {
        return movies.size
    }




}