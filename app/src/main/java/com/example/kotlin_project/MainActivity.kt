package com.example.kotlin_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.data.Product
import com.example.kotlin_project.data.productList
import com.example.kotlin_project.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var productsListView: List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // getting activity_main.xml from XML to View-like this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // primary thing in activity

        val productsAdapter = MenuCardAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = productsAdapter

        this.productsListView = productList(this.resources)

        productsAdapter.submitList(productsListView)
    }
}