package com.system.proyecto_pets.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.FragmentHomeBinding
import com.system.proyecto_pets.model.ViewPageAdapter
import me.relex.circleindicator.CircleIndicator3


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    private lateinit var view_pager2:ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        postList()

        view_pager2 = binding.viewPager2

        view_pager2.adapter = ViewPageAdapter(titleList,descList,imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator:CircleIndicator3 = binding.indicator
        indicator.setViewPager(view_pager2)

        return binding.root

    }

    private fun addToList(title:String,description:String,image:Int){
        titleList.add(title)
        descList.add(description)
        imagesList.add(image)

    }

    private fun postList(){
        for(i in 1..5){
            addToList("Titulo $i","Descripcion $i",R.mipmap.ic_launcher_round)
        }
    }


}