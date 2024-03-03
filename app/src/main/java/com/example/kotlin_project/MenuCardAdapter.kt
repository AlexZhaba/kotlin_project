package com.example.kotlin_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin_project.data.Product

class MenuCardAdapter: ListAdapter<Product, MenuCardAdapter.ProductViewHolder>(ProductDiffCallback) {

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val productTextTitleView: TextView = itemView.findViewById(R.id.productCardTitle)
        private val productImageView: ImageView = itemView.findViewById(R.id.productCardImage)
        private var currentProduct: Product? = null

        fun bind(product: Product) {
            currentProduct = product
            productTextTitleView.text = product.title
            if (product.image !== null) {
                productImageView.setImageResource(product.image)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_card_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }
}

object ProductDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return  oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }
}















