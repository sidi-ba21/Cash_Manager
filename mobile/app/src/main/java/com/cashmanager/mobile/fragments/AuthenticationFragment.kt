package com.cashmanager.mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cashmanager.mobile.R
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cashmanager.mobile.fragments.ScanFragment
import com.android.volley.toolbox.StringRequest;
import com.cashmanager.mobile.MainActivity
import org.json.JSONObject

class AuthenticationFragment: Fragment() {

    private val BASE_URL = "http://10.0.2.2:8080/api/v1/auth/login"
    //private val BASE_URL = "http://10.0.2.2:8080/api/v1/clients"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText = view?.findViewById<EditText>(R.id.name_email_login)
        val passwordEditText = view?.findViewById<EditText>(R.id.name_password_login)
        val loginButton = view?.findViewById<Button>(R.id.name_button_login)
        // Ajouter un Listener au bouton de connexion
        loginButton?.setOnClickListener {
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                authenticateUser(email, password)
            } else {
                Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun authenticateUser(email: String, password: String) {
        val requestQueue = Volley.newRequestQueue(requireContext())
        val params = JSONObject()
        params.put("email", email)
        params.put("password", password)
        val stringRequest = object : StringRequest(
            Method.POST, BASE_URL,
            Response.Listener { response ->
                if (response.isNotEmpty()) {
                    (activity as? MainActivity)?.updateLoginStatus(true)
                }
            },
            Response.ErrorListener { error ->
                println(error)
                Toast.makeText(context, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }) {
            override fun getBodyContentType(): String = "application/json"

            override fun getBody(): ByteArray {
                val jsonObject = JSONObject().apply {
                    put("email", email)
                    put("password", password)
                }
                return jsonObject.toString().toByteArray()
            }
        }
        requestQueue.add(stringRequest)
    }

}

