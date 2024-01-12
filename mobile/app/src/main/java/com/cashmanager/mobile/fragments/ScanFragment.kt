package com.cashmanager.mobile.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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



    // Appelé lorsque la vue du fragment est créée
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_scan, container, false)

        val imageItemScan: ImageView = view.findViewById(R.id.image_item_scan)
        imageItemScan.setOnClickListener {
            val intentIntegrator = IntentIntegrator(activity)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setPrompt("Scan a QR code")
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            intentIntegrator.initiateScan()
        }



        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        verticalRecyclerView?.adapter = ListAdapter(R.layout.item_vertical_shopping)

        return view
    }

    @Deprecated("Explanation of why this method is deprecated")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Récupération du résultat du scanning avec ZXing
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            val contents: String? = result.contents
            if (contents != null) {
                println(contents)
            }
        }
    }
}
