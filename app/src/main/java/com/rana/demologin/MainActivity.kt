package com.rana.demologin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import com.rana.demologin.utils.PrefsHelper

class MainActivity : AppCompatActivity() {
    private lateinit var usernameInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var loginButton: MaterialButton
    private lateinit var registerButton: MaterialButton
    private lateinit var forgotPasswordButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (PrefsHelper.isLoggedIn(this)) {
            startActivity(Intent(this, Container::class.java))
            finish()
            return
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        usernameLayout = findViewById(R.id.usernameLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            if (validateInputs()) {
                performLogin()
            }
        }

        registerButton.setOnClickListener {
            navigateToMobileNum()
        }

        forgotPasswordButton.setOnClickListener {
            navigateToMobileNum()
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        // Validate username
        if (usernameInput.text.isNullOrEmpty()) {
            usernameLayout.error = "Username is required"
            isValid = false
        } else {
            usernameLayout.error = null
        }

        // Validate password
        if (passwordInput.text.isNullOrEmpty()) {
            passwordLayout.error = "Password is required"
            isValid = false
        } else if (passwordInput.text?.length ?: 0 < 6) {
            passwordLayout.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordLayout.error = null
        }

        return isValid
    }

    private fun performLogin() {
        lifecycleScope.launch {
            try {
                // Here you would normally make an API call to your backend
                // For now, we'll simulate a successful login
                val username = usernameInput.text.toString()
                val password = passwordInput.text.toString()

                // Simulate API call delay
                kotlinx.coroutines.delay(1000)

                // For demo purposes, accept any non-empty credentials
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    onLoginSuccess()
                } else {
                    onLoginError("Invalid credentials")
                }
            } catch (e: Exception) {
                onLoginError(e.message ?: "Login failed")
            }
        }
    }

    private fun onLoginSuccess() {
        // Save login state
        getSharedPreferences("login_prefs", MODE_PRIVATE)
            .edit()
            .putBoolean("is_logged_in", true)
            .apply()

        // Navigate to Container activity
        startActivity(Intent(this, Container::class.java))
        finish() // Close login activity
    }

    private fun onLoginError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToMobileNum() {
        // Create intent for Container activity
        val intent = Intent(this, Container::class.java).apply {
            // Add flag to start fresh instance
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        // Start Container activity
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}