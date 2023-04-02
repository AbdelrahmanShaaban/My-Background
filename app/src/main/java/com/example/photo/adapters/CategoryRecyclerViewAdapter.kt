package com.example.photo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Category
import com.example.photo.R
import com.example.photo.databinding.CategoryItemBinding

class CategoryRecyclerViewAdapter(
    private val categoryList: List<Category> ,
    private val itemClick : CategoryListener
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCategory = categoryList[position]
        holder.binding.apply {
            categoryName.text = currentCategory.categoryName
            Glide.with(holder.itemView.context)
                .load(currentCategory.categoryImageUrl)
                .centerCrop()
                .error(R.color.teal_200)
                .into(categoryImageView)
        }

        holder.itemView.setOnClickListener{
            itemClick.onClickCategory(currentCategory , it)
        }
    }
}