package com.system.proyecto_pets.viewmodel.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsuarioDao(context:Context):SQLiteOpenHelper(context, DB_NAME, null , DB_VERSION){

    companion object{
        //nombre bd
        private val DB_NAME = "bdPets"
        private val DB_VERSION = 1
        //nombre de tabla
        private val TABLE_NAME = "tb_usuario"
        //columnas
        private val ID = "id"
        private val NOMBRE = "nombre"
        private val APELLIDO = "apellido"
        private val DNI = "dni"
        private val EMAIL = "email"
        private val PASSWORD = "password"

    }

    //CREACION DE BD
    override fun onCreate(p0: SQLiteDatabase?) {
        //QUERY DE CREACION
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NOMBRE TEXT, $APELLIDO TEXT, $DNI TEXT, $EMAIL TEXT, $PASSWORD TEXT);"
        //EJECUTAR  QUERY
        p0?.execSQL(CREATE_TABLE)

    }

    //SI EN CASO EXISTE LA DB , DROP
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }

    


}