package com.system.proyecto_pets.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.system.proyecto_pets.model.Usuario
import com.system.proyecto_pets.viewmodel.dao.UsuarioDao

class UsuarioViewModel : ViewModel() {

     //permite a nuestro activity suscribirse a un modelo de datos y que se llame auto cuando se reliza un cambio

    val usuario = MutableLiveData<Usuario>()


    //agregar Usuario
     fun addUser(user:Usuario,context:Context):Long{

        val sqLiteHelper =  UsuarioDao(context)

        return  sqLiteHelper.addUsuario(user)

    }

    //obtener Usuario
    fun getUser(context:Context):List<Usuario>{
        val sqLiteHelper =  UsuarioDao(context)
        return sqLiteHelper.getUsuario()
    }

    //validar Usuario

    fun validarUser(user:String , pass:String, context:Context): Usuario? {
        val sqLiteHelper =  UsuarioDao(context)
        return sqLiteHelper.validar(user, pass)
    }

    //Eliminar Usuario
    fun eliminarUser(id:Int, context: Context):Int{
        val sqLiteHelper =  UsuarioDao(context)
        return sqLiteHelper.eliminar(id)
    }

    fun editarUser(user:Usuario, context: Context):Int{
        val sqlLiteHelper = UsuarioDao(context)
        return sqlLiteHelper.updateUser(user)
    }

}