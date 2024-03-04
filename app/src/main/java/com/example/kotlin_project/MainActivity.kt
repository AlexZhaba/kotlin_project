package com.example.kotlin_project

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.data.Product
import com.example.kotlin_project.data.productList
import com.example.kotlin_project.databinding.ActivityMainBinding

interface SearchListener {
    fun onSearchChanged(searchString: String)
}

class SearchWatcher: TextWatcher {
    private val searchListener: SearchListener
    constructor(listener: SearchListener) {
        searchListener = listener
    }
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (searchString !== null) {
            searchListener.onSearchChanged(searchString.toString())
        }
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}

class MainActivity : AppCompatActivity(), SearchListener {

    private lateinit var binding: ActivityMainBinding
    private var productsListView: List<Product> = listOf()
    private var productFilterString = ""
    private var productsAdapter: MenuCardAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // getting activity_main.xml from XML to View-like this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // primary thing in activity

        setProductAdapter()
        setButtonListener()
        setPhoneListener()
    }

    private fun getFilteredProducts(): ArrayList<Product> {
        return ArrayList(productsListView
            .filter { it.title.contains(productFilterString, ignoreCase = true) })
    }

    override fun onSearchChanged(searchString: String) {
        this.productFilterString = searchString
        productsAdapter?.submitList(getFilteredProducts())
    }

    private fun setProductAdapter() {
        productsAdapter = MenuCardAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = productsAdapter

        this.productsListView = productList(this.resources)

        productsAdapter?.submitList(productsListView)
    }


    private fun setButtonListener() {
        val searchButton = findViewById<Button>(R.id.searchButton)
        val searchInput = findViewById<EditText>(R.id.textInput)

        searchButton.setOnClickListener {
            val filteredProducts = getFilteredProducts()

            startActivity(SearchResultActivity.createIntent(
                this,
                productFilterString,
                filteredProducts)
            )
        }

        searchInput.addTextChangedListener( SearchWatcher(this))
    }

    private fun setPhoneListener() {
        val telButton = findViewById<Button>(R.id.phoneButton)

        telButton.setOnClickListener {
            val phone = findViewById<EditText>(R.id.phoneInput)
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${phone.text}")
            }
            startActivity(intent)
        }
    }

}