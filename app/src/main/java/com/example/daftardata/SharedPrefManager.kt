package com.example.daftardata

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SafeWalkPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER_NAME = "userName"
        private const val KEY_USER_EMAIL = "userEmail"
        private const val KEY_EMERGENCY_MESSAGE = "emergencyMessage"
        private const val KEY_SHAKE_SOS = "shakeToSos"
        private const val KEY_SILENT_MODE = "silentMode"
    }

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    var userName: String?
        get() = sharedPreferences.getString(KEY_USER_NAME, "Aliya Nur Azizah")
        set(value) = sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()

    var userEmail: String?
        get() = sharedPreferences.getString(KEY_USER_EMAIL, "aliya.nur@gmail.com")
        set(value) = sharedPreferences.edit().putString(KEY_USER_EMAIL, value).apply()

    var emergencyMessage: String?
        get() = sharedPreferences.getString(KEY_EMERGENCY_MESSAGE, "Saya dalam bahaya! Tolong bantu saya di lokasi ini segera.")
        set(value) = sharedPreferences.edit().putString(KEY_EMERGENCY_MESSAGE, value).apply()

    var isShakeToSosEnabled: Boolean
        get() = sharedPreferences.getBoolean(KEY_SHAKE_SOS, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_SHAKE_SOS, value).apply()

    var isSilentModeEnabled: Boolean
        get() = sharedPreferences.getBoolean(KEY_SILENT_MODE, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_SILENT_MODE, value).apply()

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}
