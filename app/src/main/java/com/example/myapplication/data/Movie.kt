package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie (
        //@PrimaryKey(autoGenerate = true)
        @PrimaryKey val id: Int,
        var name: String? = null,
        var description: String? = null,
        var img: Int,
        var favorite : Boolean
)
