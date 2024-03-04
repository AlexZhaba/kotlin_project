package com.example.kotlin_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.data.Product
import com.example.kotlin_project.databinding.SearchResultBinding

class SearchResultActivity: AppCompatActivity() {
    private lateinit var binding: SearchResultBinding
    private var productsListView: List<Product> = listOf()
    private var productsAdapter: MenuCardAdapter? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchStringTitle = intent.getStringExtra(SEARCH_STRING)
        val textView = findViewById<TextView>(R.id.searchStringTitle)

        textView.text = searchStringTitle
        setProductAdapter()
    }

    private fun setProductAdapter() {
        productsAdapter = MenuCardAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.searchProductList)
        recyclerView.adapter = productsAdapter

        this.productsListView = intent.getParcelableArrayListExtra(PRODUCT)!!

        productsAdapter?.submitList(productsListView)
    }

    companion object {
        const val SEARCH_STRING = "SEARCH_STRING"
        const val PRODUCT = "PRODUCT"

        fun createIntent(context: Context, searchString: String, products: ArrayList<Product>): Intent {
            return Intent(context, SearchResultActivity::class.java)
                .putExtra(SEARCH_STRING, searchString)
                .putExtra(PRODUCT, products)
        }
    }
}