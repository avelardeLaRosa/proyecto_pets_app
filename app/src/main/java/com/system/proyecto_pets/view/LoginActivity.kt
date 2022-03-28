package com.system.proyecto_pets.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.system.proyecto_pets.databinding.ActivityLoginBinding
import com.system.proyecto_pets.viewmodel.UsuarioViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private val usuario: UsuarioViewModel by viewModels()

    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = this.getSharedPreferences("session", Context.MODE_PRIVATE)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.loginBtnIngresar.setOnClickListener {
            validarUsuario()

        }

    }

    private fun validarUsuario(){
        val user = binding.etUser.text.toString().trim()
        val pass = binding.etContraseA.text.toString().trim()


        if (user.isEmpty() || pass.isEmpty()){
            errorMsg()
        }else if (usuario.validarUser(user,pass,this)!=null){

            val editor: SharedPreferences.Editor = preferences!!.edit()

            editor.putInt("id", usuario.validarUser(user,pass,this)!!.id)
            editor.putString("nombre",usuario.validarUser(user,pass,this)!!.nombre)
            editor.putString("apellido",usuario.validarUser(user,pass,this)!!.apellido)
            editor.putString("dni",usuario.validarUser(user,pass,this)!!.dni)
            editor.putString("usuario", user)
            editor.putString("password",pass)
            editor.apply()

            startActivity(Intent(this, HomeActivity::class.java))

        }else{
            Toast.makeText(this,"Usuario no existe",Toast.LENGTH_SHORT).show()
        }

    }

    private fun errorMsg(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Campos Vacios")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

    private fun errorUser(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Usuario no existe")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

    override fun onStart() {
        super.onStart()


    }



}