package com.example.daftardata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        
        bottomNavigationView.setupWithNavController(navController)

        // Notification Simulation Logic
        val alertNotification: CardView = findViewById(R.id.alertNotification)
        val btnViewAlert: TextView = findViewById(R.id.btnViewAlert)
        
        // Simulate an alert after 10 seconds for demo
        Handler(Looper.getMainLooper()).postDelayed({
            alertNotification.visibility = View.VISIBLE
        }, 10000)
        
        btnViewAlert.setOnClickListener {
            alertNotification.visibility = View.GONE
            // Navigate directly to contact detail for Ayah
            val bundle = Bundle().apply {
                putString("CONTACT_NAME", "Ayah")
            }
            navController.navigate(R.id.contactDetailFragment, bundle)
        }
    }
}
