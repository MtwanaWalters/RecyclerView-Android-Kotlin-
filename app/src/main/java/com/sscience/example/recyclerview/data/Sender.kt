package com.sscience.example.recyclerview.data

import androidx.annotation.DrawableRes

data class Sender (
    val id: Long = 0L,
    val name: String,
    @DrawableRes val avatar: Int = 0
    )