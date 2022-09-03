package com.example.instagramclone.presentation.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.instagramclone.databinding.FragmentHomeBinding
import com.example.instagramclone.domain.models.models.Post

class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val viewModel: HomeViewModel by viewModels()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllPosts()
        viewModel.posts.observe(viewLifecycleOwner) {
            if (it.isSuccessful){
                postAdapter.postList = it.body()?.posts as List<Post>
            }
        }
        binding.postRv.hasFixedSize()
        binding.postRv.adapter = postAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        return binding.root
    }
}