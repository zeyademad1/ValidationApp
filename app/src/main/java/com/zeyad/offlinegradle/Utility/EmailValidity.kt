package com.zeyad.offlinegradle.Utility

class EmailValidity {

    // true : means that email is valid
    // false : means that email isn't valid
    fun checkEmailValidation(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}