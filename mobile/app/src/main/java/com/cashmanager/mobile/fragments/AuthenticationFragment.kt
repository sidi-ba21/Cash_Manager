package com.cashmanager.mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.cashmanager.mobile.R

class AuthenticationFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText = view?.findViewById<EditText>(R.id.name_email_login)
        val passwordEditText = view?.findViewById<EditText>(R.id.name_password_login)
        val loginButton = view?.findViewById<Button>(R.id.name_button_login)

        // Ajouter un Listener au bouton de connexion
        loginButton?.setOnClickListener {
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()
            authenticateUser(email, password)
        }

        return view
    }

    private fun authenticateUser(email: String, password: String) {
        // Appel à l'API pour authentifier l'utilisateur
        // Utilisez Retrofit ou une autre bibliothèque pour envoyer les données au serveur
        // Gérez les réponses et les erreurs ici
        // Assurez-vous de gérer les appels réseau de manière asynchrone (par exemple, avec des coroutines)
    }

}