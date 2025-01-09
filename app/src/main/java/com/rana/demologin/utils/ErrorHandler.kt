package com.rana.demologin.utils

import android.content.Context
import android.widget.Toast

object ErrorHandler {
    fun showError(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
} 