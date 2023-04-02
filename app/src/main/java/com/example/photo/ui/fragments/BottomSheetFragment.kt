package com.example.photo.ui.fragments

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.data.utils.Constants
import com.example.photo.R
import com.example.photo.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import java.io.IOException


class BottomSheetFragment(private val wallUrl: String) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        initButtons()
        return binding.root
    }

    private fun initButtons() {
        binding.downloadFab.setOnClickListener {
            downloadImageFromNet(wallUrl)
        }
        binding.backgroundFab.setOnClickListener { setAsBackground(Constants.Background.homeScreen) }
        binding.lockScreenFab.setOnClickListener { setAsBackground(Constants.Background.lockScreen) }

    }
    private fun downloadImageFromNet(uri : String){
        try {
            val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val imageUrl = Uri.parse(uri)
            val request = DownloadManager.Request(imageUrl).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setMimeType("image/*")
                    .setAllowedOverRoaming(false)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setTitle("wool")
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES ,
                        File.separator+"wool"+".jpg"
                    )
            }
            downloadManager.enqueue(request)
            Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context, "Image Download failed ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setAsBackground(lockOrBackground : Int){

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            try {
                val wallpaperManager = WallpaperManager.getInstance(context)
                val image = activity?.findViewById<ShapeableImageView>(R.id.download_IV)

                if(image?.drawable == null){
                    Toast.makeText(context, "wait to download ", Toast.LENGTH_SHORT).show()
                }else{
                    val bitmap = (image.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap,null,true,lockOrBackground)
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            }catch (e: IOException){
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

}