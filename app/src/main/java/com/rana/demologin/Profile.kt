package com.rana.demologin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class Profile : Fragment() {
    private lateinit var profileImage: ImageView
    private lateinit var nameText: TextView
    private lateinit var emailText: TextView
    private lateinit var editProfileButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        loadUserData()
        setupListeners()
    }

    private fun initViews(view: View) {
        profileImage = view.findViewById(R.id.profileImage)
        nameText = view.findViewById(R.id.nameText)
        emailText = view.findViewById(R.id.emailText)
        editProfileButton = view.findViewById(R.id.editProfileButton)
    }

    private fun loadUserData() {
        // Load user data from SharedPreferences or API
        val prefs = requireContext().getSharedPreferences("user_prefs", 0)
        nameText.text = prefs.getString("name", "User Name")
        emailText.text = prefs.getString("email", "user@example.com")
    }

    private fun setupListeners() {
        editProfileButton.setOnClickListener {
            // Handle edit profile action
        }
    }
}