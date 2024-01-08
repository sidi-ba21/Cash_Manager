package com.cashmanager.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cashmanager.mobile.fragments.AuthenticationFragment
import com.cashmanager.mobile.fragments.HomeFragment
import com.cashmanager.mobile.fragments.ScanFragment
import com.cashmanager.mobile.fragments.ShoppingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(ScanFragment())
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
    }
    private fun loadFragment(frangment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, frangment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}