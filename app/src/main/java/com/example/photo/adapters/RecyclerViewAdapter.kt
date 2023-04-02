package com.example.photo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.data.utils.BlurHashDecoder
import com.example.domain.model.Data
import com.example.photo.databinding.ItemRecyclerViewBinding

class RecyclerViewAdapter(private val listener : WallpaperItemClickListener) : PagingDataAdapter<Data , RecyclerViewAdapter.ViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
    class PhotoDiffCallback : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.blurHash == newItem.blurHash
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding : ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Data){
            val blurHashDecoderAsDrawable = BlurHashDecoder.blurHashBitmap(itemView.resources , data)
           Glide.with(binding.root.context)
               .asBitmap()
               .load(data.smallImageUrl)
               .centerCrop()
               .transition(BitmapTransitionOptions.withCrossFade(80))
               .error(blurHashDecoderAsDrawable)
               .into(binding.imageView)

            itemView.setOnClickListener{
                listener.onClickItem(data , it)
            }
        }

    }


}