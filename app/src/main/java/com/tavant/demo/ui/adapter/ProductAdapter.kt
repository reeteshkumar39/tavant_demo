package com.tavant.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tavant.demo.R
import com.tavant.demo.data.model.Product

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.image == newItem.image
        }
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val asyncListDiffer = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false))
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list = asyncListDiffer.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(list.image).into(findViewById(R.id.itemImageView))
            findViewById<TextView>(R.id.itemTitleTextView).text = list.title
            findViewById<TextView>(R.id.itemCategoryTextView).text = list.category
            findViewById<TextView>(R.id.itemDescriptionTextView).text = list.description
            findViewById<TextView>(R.id.itempriceTextView).text = list.price
            setOnClickListener {
                onItemClickListener?.let {listener->
                    listener(list)
                }
            }
        }
    }
    private var onItemClickListener:((Product) -> Unit)?=null
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }
}