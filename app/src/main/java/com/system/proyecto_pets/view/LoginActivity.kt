package com.system.proyecto_pets.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.system.proyecto_pets.databinding.ActivityLoginBinding
import com.system.proyecto_pets.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


    }




}