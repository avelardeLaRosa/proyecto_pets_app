package com.system.proyecto_pets.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val id = 100


    enum class tipoProovedor{ //designa que metodo de autenticacion
        GOOGLE,
        FACEBOOK
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //AUTENTICACION CON GOOGLE
        binding.btnGoogle.setOnClickListener {

            //id token asociado a la app
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(
                R.string.default_web_client_id
            ))
                .requestEmail()
                .requestProfile()//solicitaremos el email del usuario
                .build()

            val googleClient = GoogleSignIn.getClient(this,googleConf) //cliente autenticacion de google
            googleClient.signOut()

            //startactivityforresult
            startActivityForResult(googleClient.signInIntent, id) //mostrara la pantalla de autenticacion de google al hacer click
            //le pasamos un id que es un identificador de respueste
        }

        //BOTON DE CREAR CUENTA

        binding.btnCrearUser.setOnClickListener {

            val form = Intent(this, FormActivity::class.java)
            startActivity(form)

        }
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

    private fun showHome(email:String, provider: tipoProovedor){

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)

        }
        startActivity(homeIntent)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == id){ //el request code es igual al id
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //la respuesta corresponde con la autenticacion
            try {

                val account = task.getResult(ApiException::class.java)

                if(account!=null) { //si la cuenta es distinta de nula recuperamos credencial
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(){
                        if(it.isSuccessful){
                            showHome(account.email ?: "", tipoProovedor.GOOGLE)
                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e:ApiException){
                showAlert()
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producico un error")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }
}