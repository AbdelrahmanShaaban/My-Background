package com.example.photo.adapters

import android.view.View
import com.example.domain.model.Category

interface CategoryListener  {

    fun onClickCategory(category : Category , view : View)
}