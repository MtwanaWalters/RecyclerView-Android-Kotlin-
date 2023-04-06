package com.sscience.example.recyclerview.data

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
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

