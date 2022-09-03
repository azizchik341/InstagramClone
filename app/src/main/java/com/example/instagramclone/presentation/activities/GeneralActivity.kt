package com.example.instagramclone.presentation.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.instagramclone.R
import com.example.instagramclone.databinding.ActivityGeneralBinding
import com.example.instagramclone.presentation.adapters.ViewPagerAdapter
import com.example.instagramclone.presentation.fragments.addPost.AddPostFragment
import com.example.instagramclone.presentation.fragments.home.HomeFragment
import com.example.instagramclone.presentation.fragments.profile.ProfileFragment
import com.google.android.material.tabs.TabLayoutMediator

class GeneralActivity : AppCompatActivity() {
    private val binding:ActivityGeneralBinding by lazy {
        ActivityGeneralBinding.inflate(layoutInflater)
    }
    private val fragmentList = mutableListOf<Fragment>()

//    private val adapter:ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fragmentList.add(HomeFragment())
        fragmentList.add(AddPostFragment())
        fragmentList.add(ProfileFragment())
        binding.viewPager.adapter = ViewPagerAdapter(lifecycle = lifecycle,
            list = fragmentList,
            fm = supportFragmentManager)


        TabLayoutMediator(binding.tableLayout2, binding.viewPager) { tab, index ->
            when (index) {
                0 -> {
                    Log.i("MyTag","0")
                    tab.setIcon(R.drawable.ic_home)
                    tab.text="Home"
                }
                1 -> {
                    Log.i("MyTag","1")
                    tab.setIcon(R.drawable.ic_add)
                    tab.text="Add post"

                }
                2 -> {
                    Log.i("MyTag","1")
                    tab.setIcon(R.drawable.ic_person)
                    tab.text="profile"
                }
                else -> throw Resources.NotFoundException("Position Not Found")
            }
        }.attach()

    }
}