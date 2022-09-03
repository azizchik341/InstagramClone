package com.example.instagramclone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramclone.presentation.activities.GeneralActivity
import com.example.instagramclone.presentation.activities.MainActivity
import com.parse.ParseUser
import com.rbddevs.splashy.Splashy

@SuppressLint("CustomSplashScreen")
class SplashScreen:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashScreen()
        finish()
    }

    private fun setSplashScreen(){
        Splashy(this)
            .setLogo(R.drawable.ic_person)
            .setTitle("Instagram")
            .setTitleColor(R.color.white)
            .setSubTitleSize(14f)
            .setBackgroundColor(R.color.teal_200)
            .setFullScreen(true)
            .setAnimation(Splashy.Animation.SLIDE_IN_LEFT_RIGHT, 1000)
            .setDuration(2500)
            .show()

        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() {
                val user = ParseUser.getCurrentUser()
                if (user!=null){
                    startActivity(Intent(this@SplashScreen, GeneralActivity::class.java))
                }else{
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))

                }
            }
        })
    }
}