package com.zeyad.offlinegradle.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zeyad.offlinegradle.Data.DataManager.DataManager
import com.zeyad.offlinegradle.Data.pojo.User
import com.zeyad.offlinegradle.R
import com.zeyad.offlinegradle.Utility.EmailValidity
import com.zeyad.offlinegradle.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var dataManager: DataManager
    private lateinit var emailValidity: EmailValidity
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var user: User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        dataManager = DataManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)

        }

        binding.btnSignup.setOnClickListener {
            if (handleEmail() && handlePassword()) {
                // save data in sharedPreferences
                if (dataManager.retrieveUser().email == email) binding.etEmail.error =
                    "Email is Exists , Try to LogIn "
                user = User(
                    email = email, password = password
                )
                if (dataManager.addUser(user)) {
                    Toast.makeText(context, "Account Created Successfully!", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(
                        SignUpFragmentDirections.actionSignUpFragmentToUserFragment(email)
                    )

                } else Toast.makeText(context, "Error Creating Account!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun handleEmail(): Boolean {
        var isEmailValid = true
        emailValidity = EmailValidity()
        val emailValidation = emailValidity.checkEmailValidation(binding.etEmail.text.toString())
        if (!emailValidation) {
            binding.etEmail.error = "Email is Wrong"
            isEmailValid = false
        }
        email = binding.etEmail.text.toString()
        return isEmailValid
    }

    private fun handlePassword(): Boolean {
        var isPasswordValid = true
        val passEquality =
            binding.etPassword.text.toString() == binding.etRepeatPassword.text.toString()
        if (!passEquality) {
            Toast.makeText(context, "Passwords Are Not The Same", Toast.LENGTH_SHORT).show()
            isPasswordValid = false
        }
        if (binding.etPassword.text.length < 8) {
            binding.etPassword.error = "Password Can't Be Less Than 8 characters"
            isPasswordValid = false
        }
        password = binding.etPassword.text.toString()
        return isPasswordValid
    }


}
