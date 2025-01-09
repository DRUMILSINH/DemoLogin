package com.rana.demologin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class OTP : Fragment() {
    private lateinit var otpInput: TextInputEditText
    private lateinit var verifyButton: MaterialButton
    private lateinit var resendButton: MaterialButton
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phoneNumber = it.getString(ARG_PHONE_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_o_t_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners()
    }

    private fun initViews(view: View) {
        otpInput = view.findViewById(R.id.otpInput)
        verifyButton = view.findViewById(R.id.verifyButton)
        resendButton = view.findViewById(R.id.resendButton)
    }

    private fun setupListeners() {
        verifyButton.setOnClickListener {
            verifyOTP()
        }

        resendButton.setOnClickListener {
            resendOTP()
        }
    }

    private fun verifyOTP() {
        val otp = otpInput.text.toString()
        if (otp.length != 6) {
            Toast.makeText(context, "Please enter valid OTP", Toast.LENGTH_SHORT).show()
            return
        }

        // Verify OTP logic here
        // On success, navigate to appropriate screen
    }

    private fun resendOTP() {
        // Implement resend OTP logic
        Toast.makeText(context, "OTP resent", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARG_PHONE_NUMBER = "phone_number"

        fun newInstance(phoneNumber: String) = OTP().apply {
            arguments = Bundle().apply {
                putString(ARG_PHONE_NUMBER, phoneNumber)
            }
        }
    }
}