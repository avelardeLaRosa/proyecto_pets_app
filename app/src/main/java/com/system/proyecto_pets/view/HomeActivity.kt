package com.system.proyecto_pets.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.system.proyecto_pets.R
import com.system.proyecto_pets.databinding.ActivityHomeBinding
import com.system.proyecto_pets.view.fragments.CercaFragment
import com.system.proyecto_pets.view.fragments.HomeFragment
import com.system.proyecto_pets.view.fragments.MenuFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    //BOTTOM NAVIGATIO BAR
    private lateinit var bottomNavView : BottomNavigationView
    //llamado fragments
    private val homeFragment = HomeFragment()
    private val cercaFragment = CercaFragment()
    private val menuFragment = MenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomView


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



}