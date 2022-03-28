package com.system.proyecto_pets.view

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.ActivityHomeBinding
import com.system.proyecto_pets.view.fragments.CercaFragment
import com.system.proyecto_pets.view.fragments.HomeFragment
import com.system.proyecto_pets.view.fragments.MenuFragment
import com.system.proyecto_pets.view.fragments.RazasFragment
import com.system.proyecto_pets.viewmodel.UsuarioViewModel
import com.system.proyecto_pets.viewmodel.dao.UsuarioDao

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    //BOTTOM NAVIGATIO BAR
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var drawView: DrawerLayout
    private lateinit var navView : NavigationView
    //llamado fragments
    private val homeFragment = HomeFragment()
    private val cercaFragment = CercaFragment()
    private val menuFragment = MenuFragment()
    private val razaFragment = RazasFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomView
        navView = binding.navView
        drawView = binding.drawerLayout

        setThatFragments(homeFragment)

        //al presionar boton
        btnPress(homeFragment,cercaFragment,menuFragment)





    }


    private fun btnPress(fragment: Fragment, fragment2: Fragment, fragment3: Fragment){
        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnHome -> {
                    setThatFragments(fragment)
                }
                R.id.btnCercaDeTi -> {
                    setThatFragments(fragment2)
                }
                R.id.btnMenu -> {
                    setThatFragments(fragment3)
                }
            }
            true
        }
    }

    private fun setThatFragments(fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }


    //NavView botones
    private fun navBtnPress(item:MenuItem){
        navView.setNavigationItemSelectedListener {
            when(item.itemId){
                R.id.btn_draw_home -> {
                    mostrarFragment(homeFragment)
                }
                R.id.btn_draw_edit_profile -> {
                    mostrarFragment(menuFragment)

                }
                R.id.btn_draw_CercaDeTi -> {

                    mostrarFragment(cercaFragment)

                }
            }
            true

        }
    }

    private fun mostrarFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }

    }


    //botones NAV VIEW
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navBtnPress(item)
        return true
    }


}