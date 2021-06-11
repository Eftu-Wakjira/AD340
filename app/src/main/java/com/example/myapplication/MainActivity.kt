package com.example.myapplication


import android.R.attr.password
import android.content.Intent
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.android.gms.tasks.Task;
import com.google.api.Authentication
import com.google.api.Authentication.Builder
import com.google.firebase.auth.AuthResult;
import androidx.core.view.GravityCompat





class MainActivity : AppCompatActivity() {
    lateinit var mNameField : EditText
    lateinit var mEmailField : EditText
    lateinit var mPassword : EditText

    lateinit var mPreferences: SharedPreferences
    var sharedPrefFile : String = "com.example.myapplication"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        mNameField = findViewById(R.id.displayname)
        mEmailField = findViewById(R.id.email)
        mPassword = findViewById(R.id.password)

        mNameField.setText(mPreferences.getString("username", ""))
        mEmailField.setText(mPreferences.getString("email", ""))
        mPassword.setText(mPreferences.getString("password", ""))


        val MainBtn = findViewById<Button>(R.id.button2)
        MainBtn.setOnClickListener {
            Toast.makeText(this, "Returning to Main", Toast.LENGTH_SHORT).show()

        }
        val NewBtn = findViewById<Button>(R.id.button3)
        NewBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, TrafficAdapter::class.java))

        }


        val ResetBtn = findViewById<Button>(R.id.button4)
        ResetBtn.setOnClickListener {
            Toast.makeText(this,"Reset Complete", Toast.LENGTH_SHORT).show()

        }

        val HelpBtn = findViewById<Button>(R.id.button5)
        HelpBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, TrafficCamMap::class.java))

        }



    //    fun logIn(view: View?) {
       //     Log.d("FIREBASE", "click")
       //     logIn()
       // }


        fun validateLogin(name: String, email: String, password: String): Boolean {
            var validated = true
            if (TextUtils.isEmpty(email)) {
                mEmailField.error = "Email Invalid"
                validated = false
            } else {
                mEmailField.error = null
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.error = "Password Invalid"
                validated = false
            } else {
                mPassword.error = null
            }
            if (TextUtils.isEmpty(name)) {
                mNameField.error = "Username Invalid"
                validated = false
            } else {
                mNameField.error = null
            }
            return validated
        }

        fun logIn() {
            Log.d("FIREBASE", "signIn")


            val name: String = mNameField.text.toString()
            val email: String = mEmailField.text.toString()
            val password: String = mPassword.text.toString()

            if (!validateLogin(name, email, password)) {
                return
            }


            mPreferences.edit(true) { putString("name", name) }
            mPreferences.edit(true) { putString("email", email) }
            mPreferences.edit(true) { putString("password", password) }


            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful)
                    if (task.isSuccessful) {

                        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                        val profileUpdates: UserProfileChangeRequest =
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(mNameField.text.toString())
                                .build()
                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("FIREBASE", "User profile updated.")
                                    startActivity(
                                        Intent(
                                            this@MainActivity,
                                            FirebaseActivity::class.java
                                        )
                                    )
                                }
                            }
                    } else {
                        Log.d("FIREBASE", "sign-in failed")
                        Toast.makeText(
                            this@MainActivity, "Sign In Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    companion object {
        const val EXTRA_MESSAGE = "com.example.myapplication.MESSAGE"
    }

}


