package com.example.instagramclone.presentation.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.instagramclone.databinding.FragmentProfileBinding
import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.models.Posts
import com.example.instagramclone.domain.models.models.User
import com.example.instagramclone.presentation.activities.MainActivity
import com.parse.ParseUser
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
private val binding:FragmentProfileBinding by lazy {
    FragmentProfileBinding.inflate(layoutInflater)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            logOut()
        }
        val userName = ParseUser.getCurrentUser().username
        val userEmail = ParseUser.getCurrentUser().email
        binding.nameText.text = userName
        binding.emailText.text = userEmail
    }
    private fun logOut(){
        ParseUser.logOut()
        finishAffinity(requireActivity())
        startActivity(Intent(requireActivity(),MainActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


}