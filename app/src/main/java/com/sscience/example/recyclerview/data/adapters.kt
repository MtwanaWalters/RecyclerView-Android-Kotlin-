package com.sscience.example.recyclerview.data

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sscience.example.recyclerview.R


@BindingAdapter("isStarred")
fun ImageView.setIsStarred(isStarred: Boolean?){
    isStarred?.let{
        when(it){
            true -> setImageResource(R.drawable.baseline_star_24)
            false -> setImageResource(R.drawable.outline_star_border_24)
        }
    }
}

@BindingAdapter("setAvatarCharacter")
fun TextView.setAvatarCharacter(sender: String?){
    sender?.let{
        text = it[0].uppercaseChar().toString()
    }
}

@BindingAdapter("goneIf")
fun View.setVisibility(isGone: Boolean){
    visibility = if(isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("drawableSrc")
fun ImageView.bindDrawableSrc(src: Int){
    Glide.with(context)
        .load(src)
        .apply(RequestOptions())
        .into(this)
}