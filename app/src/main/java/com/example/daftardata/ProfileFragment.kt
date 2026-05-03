package com.example.daftardata

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var prefManager: SharedPrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        
        prefManager = SharedPrefManager(requireContext())
        
        val tvProfileName: TextView = view.findViewById(R.id.tvProfileName)
        val tvProfileEmail: TextView = view.findViewById(R.id.tvProfileEmail)
        val tvInfoName: TextView = view.findViewById(R.id.tvInfoName)
        val tvInfoEmail: TextView = view.findViewById(R.id.tvInfoEmail)
        val etCustomMessage: EditText = view.findViewById(R.id.etCustomMessage)
        val btnLogout: Button = view.findViewById(R.id.btnLogout)
        
        // Load from SharedPreferences
        tvProfileName.text = prefManager.userName
        tvProfileEmail.text = prefManager.userEmail
        tvInfoName.text = prefManager.userName
        tvInfoEmail.text = prefManager.userEmail
        etCustomMessage.setText(prefManager.emergencyMessage)
        
        // Save emergency message on change
        etCustomMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                prefManager.emergencyMessage = s.toString()
            }
        })

        btnLogout.setOnClickListener {
            prefManager.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        
        return view
    }
}
