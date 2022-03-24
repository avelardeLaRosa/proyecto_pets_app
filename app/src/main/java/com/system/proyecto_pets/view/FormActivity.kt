package com.system.proyecto_pets.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.system.proyecto_pets.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener {
            onBackPressed()
        }
        binding.btnCrear.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}