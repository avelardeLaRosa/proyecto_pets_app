package com.system.proyecto_pets.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.system.proyecto_pets.databinding.ActivityLoginBinding
import com.system.proyecto_pets.databinding.ActivityMainBinding
import com.system.proyecto_pets.model.Usuario
import com.system.proyecto_pets.viewmodel.UsuarioViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private val usuario: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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



}