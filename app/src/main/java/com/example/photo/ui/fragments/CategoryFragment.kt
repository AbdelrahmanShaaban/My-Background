package com.example.photo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.utils.ApiListCategory
import com.example.domain.model.Category
import com.example.photo.adapters.CategoryListener
import com.example.photo.adapters.CategoryRecyclerViewAdapter
import com.example.photo.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment(), CategoryListener {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var recyclerViewAdapter: CategoryRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, 2)
        binding.categoryRV.layoutManager = layoutManager
        recyclerViewAdapter = CategoryRecyclerViewAdapter(ApiListCategory.list, this)
        binding.categoryRV.adapter = recyclerViewAdapter
    }

    override fun onClickCategory(category: Category, view: View) {
        val action = CategoryFragmentDirections.actionFragmentCategoryToSpecificCategoryFragment(category.categoryName)
        Navigation.findNavController(view).navigate(action)
    }

}