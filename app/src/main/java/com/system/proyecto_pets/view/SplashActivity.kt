package com.system.proyecto_pets.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000) //duracion splash
        startActivity(Intent(this, MainActivity::class.java))
        finish() //matar activitys
    }
}