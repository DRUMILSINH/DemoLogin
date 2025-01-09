package com.rana.demologin.utils

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {
    private const val PREFS_LOGIN = "login_prefs"
    private const val PREFS_USER = "user_prefs"
    private const val PREFS_SETTINGS = "settings_prefs"

    private fun getPrefs(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        getPrefs(context, PREFS_LOGIN).edit()
            .putBoolean("is_logged_in", isLoggedIn)
            .apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPrefs(context, PREFS_LOGIN)
            .getBoolean("is_logged_in", false)
    }

    fun saveUserData(context: Context, username: String, email: String) {
        getPrefs(context, PREFS_USER).edit()
            .putString("username", username)
            .putString("email", email)
            .apply()
    }

    fun clearAllData(context: Context) {
        getPrefs(context, PREFS_LOGIN).edit().clear().apply()
        getPrefs(context, PREFS_USER).edit().clear().apply()
        getPrefs(context, PREFS_SETTINGS).edit().clear().apply()
    }
} 