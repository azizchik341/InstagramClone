package com.example.instagramclone.presentation.fragments.registerFragment

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.databinding.FragmentRegisterBinding
import com.example.instagramclone.domain.models.models.User
import com.example.instagramclone.presentation.activities.GeneralActivity
import com.parse.ParseUser

class RegisterFragment : Fragment() {
    private val binding: FragmentRegisterBinding by lazy {
        FragmentRegisterBinding.inflate(layoutInflater)
    }
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                ParseUser.logIn(
                    binding.userName.text.toString(),
                    binding.userPassword.text.toString()
                )
                startActivity(Intent(requireContext(),GeneralActivity::class.java))
            }
            else{
//                viewModel.error.ob
            }
        }

        binding.btnSingUp.setOnClickListener {
            signUp()
        }

        binding.btnLog.setOnClickListener {
            launchLoginFragment()
        }

    }

    private fun signUp() {
        val user = User(
            binding.userName.text.toString(),
            binding.userPassword.text.toString(),
            binding.userEmail.text.toString()
        )
        viewModel.signUp(user)
    }
    private fun launchLoginFragment(){
        findNavController().navigate(
            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        )
    }


}