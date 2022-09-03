package com.example.instagramclone.presentation.fragments.login

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.databinding.FragmentLoginBinding
import com.example.instagramclone.presentation.activities.GeneralActivity
import com.parse.ParseUser

class LoginFragment : Fragment() {
    private val viewModel :LoginViewModel by viewModels()
    private val binding:FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner){

                ParseUser.logIn(
                    binding.logUsername.text.toString(),
                    binding.logPassword.text.toString()
                )
                startActivity(Intent(requireContext(), GeneralActivity::class.java))
            }
                viewModel.error.observe(viewLifecycleOwner){ error->
                    Toast.makeText(requireContext(),error.message,Toast.LENGTH_SHORT).show()
                }
    binding.btnLogin.setOnClickListener {
        login()
    }
        binding.loginSignUp.setOnClickListener {
            launchRegisterFragment()
        }
    }
    private fun launchRegisterFragment(){
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        )
    }
    private fun login(){
        viewModel.login(
            binding.logUsername.text.toString(),
            binding.logPassword.text.toString()
        )
    }
}