package com.system.proyecto_pets.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.FragmentMenuBinding
import com.system.proyecto_pets.model.Usuario
import com.system.proyecto_pets.view.LoginActivity
import com.system.proyecto_pets.view.PreLoginActivity
import com.system.proyecto_pets.viewmodel.UsuarioViewModel


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!


    private var preference:SharedPreferences? = null
    private var deleteUser = 0


    private val usuario: UsuarioViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {



        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference = requireContext().getSharedPreferences("session",Context.MODE_PRIVATE)


        deleteUser = preference!!.getInt("id", 0 )?:0

        camposEdit()


        binding.tvEditar.setOnClickListener{
            habilitarCampos(true)
        }

        binding.btnModificar.setOnClickListener {
            actualizar()
            habilitarCampos(false)
        }

        binding.btnEliminar.setOnClickListener {
            eliminar(deleteUser)
        }





    }

    private fun habilitarCampos(tof:Boolean){
        binding.etEditNombre.isEnabled = tof
        binding.etEditApellidos.isEnabled = tof
        binding.etEditDni.isEnabled = tof
        binding.etEditEmail.isEnabled = tof
        binding.etEditPassword.isEnabled = tof
    }


    private fun camposEdit(){


        var nombre = ""
        var apellido = ""
        var dni = ""
        var email = ""
        var pass = ""


        nombre = preference!!.getString("nombre","")?:""
        apellido = preference!!.getString("apellido","")?:""
        dni = preference!!.getString("dni","")?:""
        email = preference!!.getString("usuario","")?:""
        pass = preference!!.getString("password","")?:""

        binding.etEditNombre.setText(nombre)
        binding.etEditApellidos.setText(apellido)
        binding.etEditDni.setText(dni)
        binding.etEditEmail.setText(email)
        binding.etEditPassword.setText(pass)
    }

    private fun actualizar(){

        val nom = binding.etEditNombre.text.toString()
        val apell = binding.etEditApellidos.text.toString()
        val dni = binding.etEditDni.text.toString()
        val email = binding.etEditEmail.text.toString().trim()
        val password = binding.etEditPassword.text.toString()

        var id=0
        id = preference!!.getInt("id",0)?:0

        if(nom.isEmpty()||apell.isEmpty()||dni.isEmpty()||email.isEmpty()||password.isEmpty()){
            errorMsg()
        }else{

            val user = Usuario(
                id = id,
                nombre = nom,
                apellido = apell,
                dni = dni,
                email = email,
                password = password
            )
            if (usuario.editarUser(user,requireContext())>-1){
                Toast.makeText(requireContext(),"Se Actualizo Correctamente",Toast.LENGTH_SHORT).show()

                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.putInt("id",id)
                editor.putString("nombre",nom)
                editor.putString("apellido",apell)
                editor.putString("dni",dni)
                editor.putString("password",password)
                editor.apply()

            }else{
                errorMsg()
            }
        }

    }

    private fun eliminar(id:Int) {

        val msg = AlertDialog.Builder(activity)
        msg.setTitle("Confirmacion")
            .setMessage("¿Seguro de eliminar tu cuenta?")
            .setPositiveButton("Si"){dialog,_->
                usuario.eliminarUser(id, requireContext())
                Toast.makeText(requireContext(),"Usuario eliminado",Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(),PreLoginActivity::class.java))
                //abrir el editor del sharedpreference
                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.clear() //limipiar y ya no hay nada guardado del usuario
                editor.apply() //aplicar
                dialog.dismiss()
            }
        msg.setNegativeButton("No"){dialog,_->
            dialog.dismiss()
        }

        val alert = msg.create()
        alert.show()
    }


    //para evitar perdidas de memoria
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun errorMsg(){

        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Campos Vacios")
        builder.setPositiveButton("Aceptar",null)
        val dialog: androidx.appcompat.app.AlertDialog = builder.create()
        dialog.show()


    }


}