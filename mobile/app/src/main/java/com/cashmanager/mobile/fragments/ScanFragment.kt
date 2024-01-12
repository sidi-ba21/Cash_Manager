package com.cashmanager.mobile.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cashmanager.mobile.R
import com.cashmanager.mobile.adapter.ListAdapter
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class ScanFragment : Fragment() {

    companion object {
        private const val PERMISSION_REQUEST_CAMERA = 1
    }

    // Appelé lorsque la vue du fragment est créée
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_scan, container, false)

        val topLinearLayout: LinearLayout = view.findViewById(R.id.topLinearLayoutScan)

        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        verticalRecyclerView?.adapter = ListAdapter(R.layout.item_vertical_shopping)

        return view
    }

    // Fonction pour configurer le scanner QR code
//    private fun setupQRCodeScanner() {
//        // Vérification de la permission de la caméra
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Initialisation du scanner QR code
//            initQRCodeScanner()
//        } else {
//            // Demande de permission de la caméra si elle n'est pas accordée
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(Manifest.permission.CAMERA),
//                PERMISSION_REQUEST_CAMERA
//            )
//        }
//    }

    // Fonction pour initialiser le scanner QR code
    //private fun initQRCodeScanner() {
        // Initialisation du scanner QR code ici
        // Appel de la fonction pour démarrer le scanning automatiquement
       // startScanning()
    //}

    // Fonction pour démarrer le scanning automatiquement
//    private fun startScanning() {
//        // Utilisation de ZXing pour démarrer le scanning
//        IntentIntegrator.forSupportFragment(this)
//            .setPrompt("") // Vous pouvez laisser le prompt vide pour un démarrage automatique
//            .initiateScan()
//    }

    // Gestionnaire pour la réponse de la demande de permission de la caméra
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == PERMISSION_REQUEST_CAMERA) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // La permission de la caméra a été accordée, initialisation du scanner QR code
//                initQRCodeScanner()
//            } else {
//                // La permission de la caméra n'a pas été accordée, affichage d'un message et fin de l'activité
//                Toast.makeText(
//                    requireContext(),
//                    "La permission de la caméra est requise",
//                    Toast.LENGTH_LONG
//                ).show()
//                activity?.finish()
//            }
//        }
//    }

    // Gestionnaire pour le résultat du scanning QR code
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Récupération du résultat du scanning avec ZXing
//        val result: IntentResult =
//            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        if (result.contents != null) {
//            // Traitement du résultat du scanning (ici, simplement affichage dans un Toast)
//            Toast.makeText(requireContext(), "Scanned: ${result.contents}", Toast.LENGTH_LONG)
//                .show()
//
//            // Vous pouvez ajouter un traitement supplémentaire ici après le scanning
//
//            // Redémarrage du scanning automatiquement
//            startScanning()
//        }
//    }
}
