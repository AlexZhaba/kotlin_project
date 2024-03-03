package com.example.kotlin_project.data
import com.example.kotlin_project.R

import android.content.res.Resources

fun productList(resources: Resources): List<Product> {
    return listOf(
        Product(id = 1, title = "Product", image = R.drawable.bread),
        Product(id = 2, title = "Product 2", image = R.drawable.cola),
        Product(id = 3, title = "Product 3", image = R.drawable.tomato),
        Product(id = 4, title = "Product 4", image = R.drawable.cucumber)
    )
}