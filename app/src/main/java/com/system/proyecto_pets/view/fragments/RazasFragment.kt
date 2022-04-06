package com.system.proyecto_pets.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.FragmentMenuBinding
import com.system.proyecto_pets.databinding.FragmentRazasBinding

class RazasFragment : Fragment() {
    private var _binding: FragmentRazasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentRazasBinding.inflate(inflater, container, false)
        return binding.root
    }


}