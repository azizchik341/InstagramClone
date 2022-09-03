package com.example.instagramclone.presentation.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.instagramclone.presentation.fragments.addPost.AddPostFragment
import com.example.instagramclone.presentation.fragments.home.HomeFragment
import com.example.instagramclone.presentation.fragments.profile.ProfileFragment

class ViewPagerAdapter(list: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    private val fragmentList = list
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                HomeFragment()
            }
            1 -> {
                AddPostFragment()
            }
            2 -> {
                ProfileFragment()
            }
            else -> {throw Resources.NotFoundException("Position Not Found")
            }
    }
}
}