package com.example.photo.ui.fragments


import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.domain.model.Data
import com.example.photo.adapters.RecyclerViewAdapter
import com.example.photo.adapters.WallpaperItemClickListener
import com.example.photo.ui.fragments.base.BaseFragment
import com.example.photo.viewModels.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment: BaseFragment()  , WallpaperItemClickListener {
     private val popularViewModel: PopularViewModel by viewModels()
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun initViewModel() {
        lifecycleScope.launch {
            popularViewModel.popularPage.collectLatest{
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
        Navigation.findNavController(view).navigate(PopularFragmentDirections.actionFragmentPopularToDownLoadFragment(imageData))

    }


}