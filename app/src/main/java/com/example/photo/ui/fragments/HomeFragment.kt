package com.example.photo.ui.fragments

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.domain.model.Data

import com.example.photo.adapters.RecyclerViewAdapter
import com.example.photo.adapters.WallpaperItemClickListener
import com.example.photo.ui.fragments.base.BaseFragment
import com.example.photo.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() , WallpaperItemClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun initViewModel() {
        lifecycleScope.launch {
            homeViewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
                Log.d("initViewModel: ", it.toString())
            }
        }
    }

    /*
        private fun initRecyclerView(){

            val layoutManager = GridLayoutManager(context , 3)
            binding.photoRv.layoutManager = layoutManager
            val recyclerViewAdapter =RecyclerViewAdapter()
            lifecycleScope.launch {
                homeViewModel.photos.collect{
                    recyclerViewAdapter.submitList(it?.data)
                    binding.photoRv.adapter = recyclerViewAdapter
                }
            }*/

    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(
            data.fullImageUrl.toString(),
            data.blurHash.toString(),
            data.category.toString()
        )
        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionFragmentHomeToDownLoadFragment(imageData))
//
//        val action = HomeFragmentDirections.actionNavigationHomeToDownLoadFragment(imageData)
//        Navigation.findNavController(view).navigate(action)
//            .navigate(
//                MainFragmentDirections.actionMainFragmentToDownLoadFragment(imageData)
////                MainFragmentDirections.actionTestFragmentToDownloadFragment(
////                    imageData
////                )
//            )
    }


}