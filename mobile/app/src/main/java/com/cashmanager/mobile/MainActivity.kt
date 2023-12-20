package com.cashmanager.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cashmanager.mobile.fragments.AuthenticationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injection de notre page dans la boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, AuthenticationFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

}