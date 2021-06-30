package com.android.movie_app.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @field:Json(name = "results") val results: List<Movies>
): Parcelable