package com.example.myapplication

class MovieModel(title: String?, year: String?, image: String?) {
    private var title: String
    private var image: String
    private var year: String
    init {
        this.title = title!!
        this.image = image!!
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
    fun getImage(): String? {
        return image
    }
    fun setImage(genre: String?) {
        this.image = image!!
    }
}