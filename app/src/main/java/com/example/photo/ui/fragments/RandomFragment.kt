package com.example.photo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.domain.model.Data
import com.example.photo.R
import com.example.photo.adapters.RecyclerViewAdapter
import com.example.photo.adapters.WallpaperItemClickListener
import com.example.photo.ui.fragments.base.BaseFragment
import com.example.photo.viewModels.RandomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomFragment : BaseFragment()  , WallpaperItemClickListener {
     private val randomViewModel : RandomViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

    override fun initViewModel() {
       lifecycleScope.launch {
           randomViewModel.randomPage.collectLatest {
               recyclerViewAdapter.submitData(it)
           }

       }
    }
    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(
            data.fullImageUrl.toString(),
            data.blurHash.toString(),
            data.category.toString()
        )
        Navigation.findNavController(view).navigate(RandomFragmentDirections.actionFragmentRandomToDownLoadFragment(imageData))

    }


}