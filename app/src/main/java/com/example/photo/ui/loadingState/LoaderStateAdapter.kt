package com.example.wallpaper.paging.loadingState

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.photo.ui.loadingState.LoadStateViewHolder

class LoaderStateAdapter(
    private val retry : ()-> Unit
): LoadStateAdapter<LoadStateViewHolder>()  {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        holder.bind(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {

        return LoadStateViewHolder.create(parent , retry)
    }
}