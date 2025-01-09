package com.rana.demologin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Home : Fragment() {
    private lateinit var welcomeText: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        welcomeText = view.findViewById(R.id.welcomeText)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Get username from SharedPreferences
        val username = requireContext().getSharedPreferences("login_prefs", 0)
            .getString("username", "User")
        welcomeText.text = "Welcome, $username!"

        // Setup RecyclerView if needed
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Add your RecyclerView setup code here
    }
}