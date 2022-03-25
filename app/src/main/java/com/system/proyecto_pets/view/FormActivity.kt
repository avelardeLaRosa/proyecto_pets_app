package com.system.proyecto_pets.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.system.proyecto_pets.databinding.ActivityFormBinding
import com.system.proyecto_pets.model.Usuario
import com.system.proyecto_pets.viewmodel.UsuarioViewModel
import com.system.proyecto_pets.viewmodel.dao.UsuarioDao

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    private val usuario:UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener {
            onBackPressed()
        }
        binding.btnCrear.setOnClickListener {
            addUsuario()
        }


    }

    private fun addUsuario(){

        val nom = binding.etNombre.text.toString()
        val apell = binding.etApellidos.text.toString()
        val dni = binding.etDni.text.toString()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if(nom.isEmpty()||apell.isEmpty()||dni.isEmpty()||email.isEmpty()||password.isEmpty()){
            errorMsg()
        }else{

            val user = Usuario(
                nombre = nom,
                apellido = apell,
                dni = dni,
                email = email,
                password = password
            )
            if (usuario.addUser(user,this)>-1){
                Toast.makeText(this,"Se Registro Correctamente",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                errorMsg()
            }
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



}