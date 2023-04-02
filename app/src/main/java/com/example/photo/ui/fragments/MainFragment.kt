package com.example.photo.ui.fragments

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.photo.R
import com.example.photo.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    private val homeFragment=HomeFragment()
    private val popularFragment=PopularFragment()
    private val randomFragment=RandomFragment()
    private val categoryFragment=CategoryFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater , container , false)

        return binding.root
    }
//    private fun initBottomNavigation(){
//        binding.bottomNavigationView.background = null
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.fragment_home ->{
//                    setCurrentFragment(homeFragment)
//                    true
//                }
//                R.id.fragment_popular ->{
//                    setCurrentFragment(popularFragment)
//                    true
//                }
//                R.id.fragment_random ->{
//                    setCurrentFragment(randomFragment)
//                    true
//                }
//                R.id.fragment_category ->{
//                    setCurrentFragment(categoryFragment)
//                    true
//                }
//               else -> true
//            }
//        }
//    }

//    private fun setCurrentFragment(fragment:Fragment)=
//        parentFragmentManager.beginTransaction().apply {
//            replace(R.id.container,fragment)
//            commit()
//        }




}