package com.example.daftardata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {

    private lateinit var prefManager: SharedPrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        
        prefManager = SharedPrefManager(requireContext())
        
        val switchShake: SwitchMaterial = view.findViewById(R.id.switchShake)
        val switchSilent: SwitchMaterial = view.findViewById(R.id.switchSilent)
        val switchSiren: SwitchMaterial = view.findViewById(R.id.switchSiren)
        val switchLocation: SwitchMaterial = view.findViewById(R.id.switchLocation)
        
        // Load existing settings
        switchShake.isChecked = prefManager.isShakeToSosEnabled
        switchSilent.isChecked = prefManager.isSilentModeEnabled
        // ... Load others as needed
        
        // Save on change
        switchShake.setOnCheckedChangeListener { _, isChecked ->
            prefManager.isShakeToSosEnabled = isChecked
        }
        
        switchSilent.setOnCheckedChangeListener { _, isChecked ->
            prefManager.isSilentModeEnabled = isChecked
        }
        
        return view
    }
}
