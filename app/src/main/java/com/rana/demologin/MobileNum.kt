package com.rana.demologin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MobileNum : Fragment() {
    private lateinit var phoneInput: TextInputEditText
    private lateinit var phoneLayout: TextInputLayout
    private lateinit var continueButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobile_num, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners()
    }

    private fun initViews(view: View) {
        phoneInput = view.findViewById(R.id.phoneInput)
        phoneLayout = view.findViewById(R.id.phoneLayout)
        continueButton = view.findViewById(R.id.continueButton)
    }

    private fun setupListeners() {
        continueButton.setOnClickListener {
            if (validatePhone()) {
                sendOTP()
            }
        }
    }

    private fun validatePhone(): Boolean {
        val phone = phoneInput.text.toString()
        if (phone.isEmpty()) {
            phoneLayout.error = "Phone number is required"
            return false
        }
        if (!phone.matches(Regex("^[0-9]{10}$"))) {
            phoneLayout.error = "Enter valid 10-digit phone number"
            return false
        }
        phoneLayout.error = null
        return true
    }

    private fun sendOTP() {
        // Navigate to OTP fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, OTP.newInstance(phoneInput.text.toString()))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = MobileNum()
    }
}