package com.zeyad.offlinegradle.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zeyad.offlinegradle.Data.DataManager.DataManager
import com.zeyad.offlinegradle.R
import com.zeyad.offlinegradle.databinding.FragmentUserBinding
import yuku.ambilwarna.AmbilWarnaDialog


class UserFragment : Fragment(), AmbilWarnaDialog.OnAmbilWarnaListener {

    private lateinit var binding: FragmentUserBinding
    private lateinit var dataManager: DataManager
    private var defaultColor: Int = 0
    private var newColor = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        dataManager = DataManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultColor = ContextCompat.getColor(requireContext(), R.color.purple_500)
        binding.colorPalette.setOnClickListener {
            openPalette()
        }


        binding.userEmail.text = dataManager.retrieveUser().email.substringBefore("@")

        binding.logout.setOnClickListener {
            dataManager.removeUser()
            findNavController().navigate(R.id.action_userFragment_to_welcomeFragment)
            Toast.makeText(context, "User Logout Successfully", Toast.LENGTH_SHORT).show()
        }

        binding.check.setOnClickListener {
            val text = binding.etSomething.text.toString()
            if (text == "") binding.etSomething.error = "Can't be empty"
            binding.txtResult.setTextColor(newColor)
            binding.txtResult.text = text
        }

    }

    private fun openPalette() {
        val ambilWarnaDialog = AmbilWarnaDialog(requireContext(), defaultColor, this)
        ambilWarnaDialog.show()
    }

    override fun onCancel(dialog: AmbilWarnaDialog?) {
        TODO("Not yet implemented")
    }

    override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
        newColor = color
    }

}
