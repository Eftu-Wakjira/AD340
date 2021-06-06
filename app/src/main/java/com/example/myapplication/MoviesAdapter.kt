package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.media.Image
import android.provider.AlarmClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get

internal class MoviesAdapter(private val showMovie: (pos: Int) -> Unit) :
        RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        var title: TextView = view.findViewById(R.id.title)
        var year: TextView = view.findViewById(R.id.year)
        var actor: TextView = view.findViewById(R.id.actor)
        val movieimage: ImageView = view.findViewById(R.id.movieimage)
        val description: TextView = view.findViewById(R.id.description)

        init {
            view.setOnClickListener(this)
            val pos: Int = adapterPosition
         //   showMovie(pos)

        }

     override fun onClick(v: View?) {
         var bindingAdapterPosition =0
          val position = bindingAdapterPosition
          if (position != RecyclerView.NO_POSITION){
            //  listener.MoviesAdapter(position)
          }
      }

    }

    interface OnItemClickListener {
       fun MoviesAdapter(position: Int)
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
        Picasso.get().load(movie[3]).into(holder.movieimage)


        holder.itemView.isClickable = true
        holder.itemView.isFocusableInTouchMode = true





    }


   override fun getItemCount(): Int {
        return movies.size
    }



}


