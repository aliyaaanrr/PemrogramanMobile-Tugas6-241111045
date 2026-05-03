package com.example.daftardata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private var isSosActive = false
    private lateinit var dynamicIslandLayout: LinearLayout
    private lateinit var tvStatus: TextView
    private lateinit var statusIndicator: View
    private lateinit var btnSos: View
    private lateinit var btnCancelSos: Button
    private lateinit var tvSosDesc: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val blinkAnimation = AlphaAnimation(1.0f, 0.2f).apply {
        duration = 500
        repeatMode = Animation.REVERSE
        repeatCount = Animation.INFINITE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        dynamicIslandLayout = view.findViewById(R.id.dynamicIslandLayout)
        tvStatus = view.findViewById(R.id.tvStatus)
        statusIndicator = view.findViewById(R.id.statusIndicator)
        btnSos = view.findViewById(R.id.btnSos)
        btnCancelSos = view.findViewById(R.id.btnCancelSos)
        tvSosDesc = view.findViewById(R.id.tvSosDesc)
        
        view.findViewById<View>(R.id.ivBack).visibility = View.GONE

        btnSos.setOnLongClickListener {
            activateSos()
            true
        }

        btnCancelSos.setOnClickListener {
            deactivateSos()
        }

        return view
    }

    private fun activateSos() {
        isSosActive = true
        dynamicIslandLayout.setBackgroundResource(R.drawable.bg_dynamic_island_alert)
        tvStatus.text = getString(R.string.dashboard_status_alert)
        statusIndicator.visibility = View.GONE
        btnCancelSos.visibility = View.VISIBLE
        tvSosDesc.visibility = View.GONE
        
        dynamicIslandLayout.startAnimation(blinkAnimation)
        
        Toast.makeText(context, "SINYAL DARURAT TERKIRIM!", Toast.LENGTH_SHORT).show()
    }

    private fun deactivateSos() {
        isSosActive = false
        dynamicIslandLayout.clearAnimation()
        dynamicIslandLayout.setBackgroundResource(R.drawable.bg_dynamic_island_safe)
        tvStatus.text = getString(R.string.dashboard_status_safe)
        statusIndicator.visibility = View.VISIBLE
        btnCancelSos.visibility = View.GONE
        tvSosDesc.visibility = View.VISIBLE
        
        Toast.makeText(context, "Sinyal darurat dibatalkan", Toast.LENGTH_SHORT).show()
    }
}
