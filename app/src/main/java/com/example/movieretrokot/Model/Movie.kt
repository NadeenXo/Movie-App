package com.example.movieretrokot.Movie

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

    @Parcelize
    @Entity(tableName = "Movie_table")
    data class Movie(
        @PrimaryKey(autoGenerate = true) val id :Int,

        @SerializedName("vote_average")
        @ColumnInfo(name = "vote") val vote : String?,

        @SerializedName("title")
        @ColumnInfo(name = "title") val title : String?,


        @SerializedName("poster_path")
        @ColumnInfo(name = "poster") val poster : String?,

        @SerializedName("release_date")
        @ColumnInfo(name = "release") val release : String?,

        @ColumnInfo(name = "isFavorite") var isFavorite: Boolean

    ) : Parcelable{
        constructor() : this(0,"", "", "", "",false)

    }


    @Parcelize
    data class MovieResponse(
        @SerializedName("results")
        val movies : List<Movie>
            ) : Parcelable {
                constructor() : this(listOf())
    }



