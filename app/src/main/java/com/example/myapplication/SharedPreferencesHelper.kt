package com.example.myapplication

import android.content.SharedPreferences


class SharedPreferencesHelper


 (    private val mSharedPreferences: SharedPreferences
) {

    fun saveEntry(key: String?, message: String?): Boolean {

        val editor = mSharedPreferences.edit()
        editor.putString(key, message)


        return editor.commit()
    }

    fun getEntry(key: String?): String? {
        return mSharedPreferences.getString(key, "")
    }
}