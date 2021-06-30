package com.android.movie_app.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    @field:Json(name = "id") val movieId: Long,
    @field:Json(name = "poster_path") val posterPath: String
): Parcelable
