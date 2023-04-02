package com.example.photo.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.Data
import com.example.photo.adapters.RecyclerViewAdapter
import com.example.photo.adapters.WallpaperItemClickListener
import com.example.photo.databinding.FragmentHomeBinding
import com.example.photo.ui.fragments.HomeFragmentDirections
import com.example.wallpaper.paging.loadingState.LoaderStateAdapter


abstract class BaseFragment : Fragment() {
    abstract var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        recyclerAdapter()
        initViewModel()
        return binding.root
    }

    abstract fun initViewModel()

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.photoRv.layoutManager = layoutManager
        binding.photoRv.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter{recyclerViewAdapter.retry()},
            footer = LoaderStateAdapter{recyclerViewAdapter.retry()}

        )

        recyclerViewAdapter.addLoadStateListener { loadState->

            binding.photoRv.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
        }

    }



}