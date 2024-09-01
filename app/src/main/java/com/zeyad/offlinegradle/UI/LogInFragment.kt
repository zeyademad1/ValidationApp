package com.zeyad.offlinegradle.UI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zeyad.offlinegradle.Data.DataManager.DataManager
import com.zeyad.offlinegradle.R
import com.zeyad.offlinegradle.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var savedEmail: String
    private lateinit var savedPassword: String
    lateinit var email: String
    private lateinit var password: String
    private lateinit var dataManager: DataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(layoutInflater, container, false)
        dataManager = DataManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedEmail = dataManager.retrieveUser().email
        savedPassword = dataManager.retrieveUser().password

        binding.btnLogin.setOnClickListener {
            email = binding.etEmail.text.toString()
            password = binding.etPassword.text.toString()
            if (email == savedEmail && password == savedPassword) {
                findNavController().navigate(
                    LogInFragmentDirections.actionLogInFragmentToUserFragment()
                )
            } else {
                if (email != savedEmail) binding.etEmail.error = "Email is incorrect"
                if (password != savedPassword) binding.etPassword.error = "Password is incorrect"
            }
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }
        binding.facebook.setOnClickListener {
            openWebsiteToLogIn(com.zeyad.offlinegradle.Utility.Constants.URL.FACEBOOK)
        }
        binding.twitter.setOnClickListener {
            openWebsiteToLogIn(com.zeyad.offlinegradle.Utility.Constants.URL.TWITTER)
        }
        binding.gmail.setOnClickListener {
            openWebsiteToLogIn(com.zeyad.offlinegradle.Utility.Constants.URL.GOOGLE)
        }


    }

    private fun openWebsiteToLogIn(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }
}

