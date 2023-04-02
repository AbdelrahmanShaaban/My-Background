package com.example.photo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.Data
import com.example.photo.adapters.RecyclerViewAdapter
import com.example.photo.adapters.WallpaperItemClickListener
import com.example.photo.databinding.FragmentSpecificCategoryBinding
import com.example.photo.viewModels.CategoryViewModel
import com.example.photo.viewModels.CategoryViewModelFactory
import com.example.wallpaper.paging.loadingState.LoaderStateAdapter
import androidx.paging.LoadState
import kotlinx.coroutines.launch


class SpecificCategoryFragment : Fragment()  , WallpaperItemClickListener {
    private lateinit var categoryViewModel: CategoryViewModel
    private val args: SpecificCategoryFragmentArgs by navArgs()
    private lateinit var binding: FragmentSpecificCategoryBinding
    private var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecificCategoryBinding.inflate(inflater)
        categoryName()
        callBack()
        initRecyclerView()
        initViewModel()
        return binding.root
    }
    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallCategoriesRecyclerView.layoutManager = layoutManager
        binding.wallCategoriesRecyclerView.adapter =recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter{recyclerViewAdapter.retry()},
            footer = LoaderStateAdapter{recyclerViewAdapter.retry()}
        )

        recyclerViewAdapter.addLoadStateListener { loadState->
            binding.wallCategoriesRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.CategoryProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.CategoryButtonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }
    }
    private fun handleError(loadState: CombinedLoadStates){
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
        }
    }
    private fun initViewModel() {
        categoryViewModel = ViewModelProvider(
            this,
            CategoryViewModelFactory(args.categoryName)
        )[CategoryViewModel::class.java]

        categoryViewModel.categoryPhotos.observe(
            viewLifecycleOwner
        ){
            lifecycleScope.launch {
                    recyclerViewAdapter.submitData(it)


            }
        }
    }
    private fun categoryName() {
        binding.categoryName.text = args.categoryName
    }
    private fun callBack(){
        binding.categoryName.setOnClickListener{
           findNavController().popBackStack()
        }
    }

    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(),data.blurHash.toString())
        Navigation.findNavController(view).navigate(
            SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToDownLoadFragment(
                imageData
            )
        )    }

}