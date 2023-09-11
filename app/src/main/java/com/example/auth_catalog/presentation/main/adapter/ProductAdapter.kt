package com.example.auth_catalog.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.auth_catalog.R
import com.example.auth_catalog.databinding.ViewHolderProductBinding
import com.example.auth_catalog.domain.entity.ProductEntity

/**
 * Adapter class to list product.
 */
class ProductAdapter(
    private val products: List<ProductEntity>
) : RecyclerView.Adapter<ProductAdapter.ContactViewHolder>() {

    class ContactViewHolder(var binding: ViewHolderProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** Generates a new instance of [onCreateViewHolder]. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ViewHolderProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    /** Returns the total number of contacts in the dataset. */
    override fun getItemCount(): Int {
        return products.size
    }

    /** Binds data to the ContactViewHolder at the specified position. */
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val context = holder.itemView.context
        val product: ProductEntity = products[position]
        holder.binding.tvName.text = product.title
        holder.binding.tvPrice.text =
            context.getString(R.string.product_price, product.price.toString())
        holder.binding.tvDescription.text = product.description
        Glide.with(holder.itemView)
            .load(product.images.first())
            .into(holder.binding.ivImage)
    }

}