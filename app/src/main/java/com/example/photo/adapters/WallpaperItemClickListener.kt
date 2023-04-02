package com.example.photo.adapters

import android.view.View
import com.example.domain.model.Data

interface WallpaperItemClickListener {

    fun onClickItem(data : Data, view: View)
}