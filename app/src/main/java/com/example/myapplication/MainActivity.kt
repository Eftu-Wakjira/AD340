package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val SubmitBtn = findViewById<Button>(R.id.button)
        SubmitBtn.setOnClickListener {
            Toast.makeText(this,"Sumbitted", Toast.LENGTH_SHORT).show()

        }
        val MainBtn = findViewById<Button>(R.id.button2)
        MainBtn.setOnClickListener {
            Toast.makeText(this,"Returning to Main", Toast.LENGTH_SHORT).show()

        }
        val NewBtn = findViewById<Button>(R.id.button3)
        NewBtn.setOnClickListener {
            Toast.makeText(this,"Creating New", Toast.LENGTH_SHORT).show()

        }


        val ResetBtn = findViewById<Button>(R.id.button4)
        ResetBtn.setOnClickListener {
            Toast.makeText(this,"Reset Complete", Toast.LENGTH_SHORT).show()

        }

        val HelpBtn = findViewById<Button>(R.id.button5)
        HelpBtn.setOnClickListener {
            Toast.makeText(this,"Help is on the way!", Toast.LENGTH_SHORT).show()

        }


    }
}

