package com.system.proyecto_pets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.system.proyecto_pets.model.Usuario

class UsuarioViewModel : ViewModel() {

    //permite a nuestro activity suscribirse a un modelo de datos y que se llame auto cuando se reliza un cambio

    val usuario = MutableLiveData<Usuario>()


}