package com.system.proyecto_pets.viewmodel.dao

import android.R.attr.id
import android.R.attr.password
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.RoomMasterTable
import com.system.proyecto_pets.model.Usuario


class UsuarioDao(val context:Context):SQLiteOpenHelper(context, DB_NAME, null , DB_VERSION){

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


    //Insertar Usuario
    fun addUsuario(user:Usuario):Long{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, user.id)
        contentValues.put(NOMBRE, user.nombre)
        contentValues.put(APELLIDO, user.apellido)
        contentValues.put(DNI, user.dni)
        contentValues.put(EMAIL, user.email)
        contentValues.put(PASSWORD, user.password)

        val success = db.insert(TABLE_NAME, null, contentValues )
        db.close()

        return success

    }

    fun updateUser(user:Usuario):Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, user.id)
        contentValues.put(NOMBRE, user.nombre)
        contentValues.put(APELLIDO, user.apellido)
        contentValues.put(DNI, user.dni)
        contentValues.put(EMAIL, user.email)
        contentValues.put(PASSWORD, user.password)

        val sucess = db.update(TABLE_NAME,contentValues, "id=${user.id}",null)
        db.close()
        return sucess

    }


    //obtener usuario
    fun getUsuario(): List<Usuario>{

        val listaUser = ArrayList<Usuario>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = writableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id:Int
        var nombre:String
        var apellido:String
        var dni:String
        var email:String
        var password:String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                apellido = cursor.getString(cursor.getColumnIndex("apellido"))
                dni = cursor.getString(cursor.getColumnIndex("dni"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                password = cursor.getString(cursor.getColumnIndex("password"))

                val user = Usuario(
                    id = id,
                    nombre = nombre,
                    apellido = apellido,
                    dni = dni,
                    email = email,
                    password = password
                    )

                listaUser.add(user)

            }while (cursor.moveToNext())
        }
        return listaUser
    }


    //VALIDAR USUARIO Y CONTRASEÑA

    fun validar(us:String, pass: String):Usuario? {
        val db = writableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $EMAIL = '$us' AND $PASSWORD = '$pass'"


        val cursor: Cursor?
        val user: Usuario?

        try {
            cursor = db.rawQuery(query, null)
            cursor.moveToFirst()
            user = Usuario(
                id = cursor.getInt(cursor.getColumnIndex("id")),
                nombre = cursor.getString(cursor.getColumnIndex("nombre")),
                apellido = cursor.getString(cursor.getColumnIndex("apellido")),
                dni = cursor.getString(cursor.getColumnIndex("dni")),
                email = cursor.getString(cursor.getColumnIndex("email")),
                password = cursor.getString(cursor.getColumnIndex("password"))
            )



        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            return null
        }
        return user

    }

    fun eliminar(id:Int):Int{

            val db = this.writableDatabase

            val result = db.delete(TABLE_NAME, "id = $id", null)


        return result
    }
}