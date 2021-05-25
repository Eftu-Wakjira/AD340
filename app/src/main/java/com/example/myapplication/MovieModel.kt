package com.example.myapplication

class MovieModel(title: String?, year: String?, image: String?) {
    private var title: String
    private var year: String
    init {
        this.title = title!!
        this.year = year!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getYear(): String? {
        return year
    }
    fun setYear(year: String?) {
        this.year = year!!
    }






}