package com.ashwani.nytimes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ashwani.nytimes.R
import com.ashwani.nytimes.databinding.FragmentSignupBinding
import com.ashwani.nytimes.utils.ValidatorUtils
import com.ashwani.nytimes.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        setupUi()

        return binding.root
    }

    private fun setupUi() {
        binding.btSignup.setOnClickListener(View.OnClickListener {
//            if (checkAllFields()) {
            findNavController().navigate(R.id.action_signupFragment_to_dashboardFragment)
//            }
        })
    }

    private fun checkAllFields(): Boolean {
        binding.tvPasswordError.visibility = View.INVISIBLE

        if (!ValidatorUtils.isNameValid(binding.etName.text.toString())) {
            binding.etName.error = getString(R.string.name_error)
            return false
        }
        if (!ValidatorUtils.isValidEmail(binding.etEmail.text.toString())) {
            binding.etEmail.error = getString(R.string.email_error)
            return false
        }
        if (!ValidatorUtils.isValidPassword(binding.etPassword.text.toString())) {
            binding.etPassword.error = getString(R.string.password_error_ph)
            binding.tvPasswordError.visibility = View.VISIBLE
            return false
        }
        return true
    }

}