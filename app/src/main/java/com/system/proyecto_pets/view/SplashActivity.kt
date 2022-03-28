package com.system.proyecto_pets.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    private var preferences: SharedPreferences? = null
    private var userSession = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        preferences = this.getSharedPreferences("session", Context.MODE_PRIVATE)
        userSession = preferences!!.getString("usuario","")?:"" //obteniendo el usuario

        Thread.sleep(1000)
        validateSesion()
        finish() //matar activitys
    }

    fun validateSesion(){

        if(userSession.isEmpty()){
            startActivity(Intent(this, PreLoginActivity::class.java))
        }else{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}