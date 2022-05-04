package com.system.proyecto_pets.view

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.graphics.toColor
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.ActivityHomeBinding
import com.system.proyecto_pets.databinding.NavHeaderBinding
import com.system.proyecto_pets.view.fragments.CercaFragment
import com.system.proyecto_pets.view.fragments.HomeFragment
import com.system.proyecto_pets.view.fragments.MenuFragment
import com.system.proyecto_pets.view.fragments.RazasFragment
import com.system.proyecto_pets.viewmodel.UsuarioViewModel
import com.system.proyecto_pets.viewmodel.dao.UsuarioDao

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toogle: ActionBarDrawerToggle


    //llamado fragments
    private val homeFragment = HomeFragment()
    private val cercaFragment = CercaFragment()
    private val menuFragment = MenuFragment()
    private val razaFragment = RazasFragment()

    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toogle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open_drawer, R.string.close_drawer)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState() //sincroniza icono de menu y panel de navegacion
        //cambios al toolbar
        supportActionBar?.setTitle("Pets App") //cambiar titulo
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferences = this.getSharedPreferences("session", Context.MODE_PRIVATE)

        //botones fragments
        setFragmentos()


    }

    override fun onStart() {
        super.onStart()
        //data en headerview
        cargarData()

    }

    @SuppressLint("SetTextI18n")
    private fun cargarData() {
        var tvNombre: TextView? = null
        val nombre = preferences!!.getString("nombre","")?:""
        val apellido = preferences!!.getString("apellido","")?:""

        if(preferences !=null){
            val vistaHeader = binding.navView.getHeaderView(0)
            var tvNombre:TextView = vistaHeader.findViewById(R.id.tvHeaderNom)

            tvNombre.text = "$nombre $apellido"
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFragmentos(){
        binding.navView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.btn_draw_home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,HomeFragment())
                        commit()
                    }
                }
                R.id.btn_draw_edit_profile -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,MenuFragment())
                        commit()
                    }
                }
                R.id.btn_draw_CercaDeTi -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,CercaFragment())
                        commit()
                    }
                }
                R.id.btn_draw_razas -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,RazasFragment())
                        commit()
                    }
                }
                R.id.btn_draw_sign_out -> {
                    val editor:SharedPreferences.Editor = preferences!!.edit()
                    editor.clear()
                    editor.apply()
                    startActivity(Intent(this,PreLoginActivity::class.java))
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }
    /*

                    val editor:SharedPreferences.Editor = preferences!!.edit()
                    editor.clear()
                    editor.apply()
                    startActivity(Intent(this,PreLoginActivity::class.java))
                }

            }
            //cierra el drawer al abrir fragment
            binding.drawerLayout.closeDrawer(GravityCompat.START)

            true

        }*/
    }