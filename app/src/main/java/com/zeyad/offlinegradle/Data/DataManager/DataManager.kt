package com.zeyad.offlinegradle.Data.DataManager

import android.content.Context
import android.content.SharedPreferences
import com.zeyad.offlinegradle.Data.pojo.User
import com.zeyad.offlinegradle.Utility.Constants

class DataManager(context: Context?) {
    /*
     * TODO: persist data by SharedPreferences
     */
    private val sharedPreferences: SharedPreferences =
        context!!.getSharedPreferences(Constants.Sharedkeys.SHARED_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun addUser(user: User): Boolean { // return true if saved successfully
        editor.putString(Constants.Sharedkeys.EMAIL_KEY, user.email)
        editor.putString(Constants.Sharedkeys.PASSWORD_KEY, user.password)

        return editor.commit()
    }

    fun retrieveUser(): User {
        val email =
            sharedPreferences.getString(Constants.Sharedkeys.EMAIL_KEY, "noEmail").toString()
        val password =
            sharedPreferences.getString(Constants.Sharedkeys.PASSWORD_KEY, "password").toString()
        return User(
            email = email,
            password = password
        )
    }

    fun removeUser() {
        editor.clear()
        editor.apply()
    }
}