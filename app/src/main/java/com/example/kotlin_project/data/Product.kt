package com.example.kotlin_project.data

import androidx.annotation.DrawableRes

data class Product(
    val id: Long,
    val title: String,
    @DrawableRes
    val image: Int?
)