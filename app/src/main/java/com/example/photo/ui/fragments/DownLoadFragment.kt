package com.example.photo.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.data.utils.BlurHashDecoder
import com.example.photo.R
import com.example.photo.databinding.FragmentDownLoadBinding


class DownLoadFragment : Fragment() {
    private lateinit var binding: FragmentDownLoadBinding
    private val args: DownLoadFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDownLoadBinding.inflate(inflater)
        loadImage(args.imageData[0])
        addCallBack()
        bottomSheet()
        return binding.root
    }
    private fun loadImage(url: String) {
        var blurHash = BlurHashDecoder.decode(args.imageData[1])
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(blurHash?.toDrawable(this.resources))
            .error(blurHash)
            .into(binding.downloadIV)

        binding.constraint.background = BitmapDrawable(this.resources , blurHash)

    }
    private fun addCallBack(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun bottomSheet(){
        val bottomSheet = BottomSheetFragment(args.imageData[0])
        binding.downloadDetailsFab.setOnClickListener{
            bottomSheet.show(requireActivity().supportFragmentManager , "bottomSheet")
        }
    }
}