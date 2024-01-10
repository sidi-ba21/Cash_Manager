package com.cashmanager.mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cashmanager.mobile.fragments.AuthenticationFragment
import com.cashmanager.mobile.fragments.HomeFragment
import com.cashmanager.mobile.fragments.ScanFragment
import com.cashmanager.mobile.fragments.ShoppingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE)

        // Vérification si la clé "isLoggedIn" est définie
        if (sharedPreferences.contains("isLoggedIn")) {
             //Si la clé n'existe pas, initialisation à false
            with(sharedPreferences.edit()) {
//                putBoolean("isLoggedIn", false)
//                apply()
            }
        }

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            showLoggedInView()
        } else {
            loadFragment(AuthenticationFragment())
        }

    }

    private fun showLoggedInView() {
        val navigationBottonView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationBottonView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.shopping_page -> {
                    loadFragment(ShoppingFragment())
                    true
                }
                R.id.scan_page -> {
                    loadFragment(ScanFragment())
                    true
                }

                else -> false
            }
        }
        loadFragment(ScanFragment())
    }

     private fun loadFragment(frangment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, frangment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun updateLoginStatus(status: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", status)
        editor.apply()
        recreate()
    }

}