package com.android.movie_app.ui

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.request.CachePolicy

@BindingAdapter(value = ["movieUrl"])
fun loadImage(view: ImageView, imageUrl: String?){

    if(!TextUtils.isEmpty(imageUrl))
        view.load("https://image.tmdb.org/t/p/w185$imageUrl") {
            crossfade(true)
            memoryCachePolicy(CachePolicy.DISABLED)
        }
}

/*
* @BindingAdapter(value = ["updatedAt"])
fun repoUpdateText(textView : TextView, updatedAt : String?) {
    if(!TextUtils.isEmpty(updatedAt)) {

        val uiHelper = UiHelper(textView.context)
        val formattedTime = updatedAt?.let { uiHelper.getProjectUpdatedTime(it) }

        if(!TextUtils.isEmpty(formattedTime) && formattedTime != "") {
            val updatedTime = textView.context.resources.getString(R.string.updated)+" "+ formattedTime
            textView.text = updatedTime
        }
        else textView.visibility = View.GONE
    }
}*/