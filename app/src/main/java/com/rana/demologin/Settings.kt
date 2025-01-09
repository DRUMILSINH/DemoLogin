package com.rana.demologin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.button.MaterialButton
import com.rana.demologin.utils.PrefsHelper

class Settings : Fragment() {
    private lateinit var notificationSwitch: SwitchMaterial
    private lateinit var darkModeSwitch: SwitchMaterial
    private lateinit var logoutButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        loadSettings()
        setupListeners()
    }

    private fun initViews(view: View) {
        notificationSwitch = view.findViewById(R.id.notificationSwitch)
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch)
        logoutButton = view.findViewById(R.id.logoutButton)
    }

    private fun loadSettings() {
        val prefs = requireContext().getSharedPreferences("settings_prefs", 0)
        notificationSwitch.isChecked = prefs.getBoolean("notifications_enabled", true)
        darkModeSwitch.isChecked = prefs.getBoolean("dark_mode_enabled", false)
    }

    private fun setupListeners() {
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            requireContext().getSharedPreferences("settings_prefs", 0)
                .edit()
                .putBoolean("notifications_enabled", isChecked)
                .apply()
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            requireContext().getSharedPreferences("settings_prefs", 0)
                .edit()
                .putBoolean("dark_mode_enabled", isChecked)
                .apply()
            // Implement dark mode toggle
        }

        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        PrefsHelper.clearAllData(requireContext())
        startActivity(Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}