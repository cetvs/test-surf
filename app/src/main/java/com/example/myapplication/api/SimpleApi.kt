package com.example.myapplication.api

import com.example.myapplication.data.Movie
import retrofit2.http.GET

interface SimpleApi {
    @GET("/3/movie/550?api_key=461062f3ca455541c4c57750fcbf6759")
    suspend fun getMovie(): Movie
}