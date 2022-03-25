package com.system.proyecto_pets.model

import java.util.*

data class Usuario(
    var id: Int = getAutoId(),
    var nombre:String = "",
    var apellido:String = "",
    var dni:String = "",
    var email:String = "",
    var password:String = ""
) {
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}