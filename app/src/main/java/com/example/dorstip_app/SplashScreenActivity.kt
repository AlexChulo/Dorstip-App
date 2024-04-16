package com.example.dorstip_app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.SubscriptSpan
import android.widget.TextView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        Initializing and animation of the textview
        val tvSplashScreenTitle: TextView = findViewById(R.id.tvSplashScreenTitle)
        val tvSplashScreenSubtitle: TextView = findViewById(R.id.tvSplashScreenSubtitle)
        val tvSplashScreenDot: TextView = findViewById(R.id.tvSplashScreenDot)
        tvSplashScreenTitle.alpha = 0f
        tvSplashScreenSubtitle.alpha = 0f
        tvSplashScreenDot.alpha = 0f

        // Animate the TextViews
        tvSplashScreenTitle.animate().setDuration(1500).alpha(1f)
        tvSplashScreenSubtitle.animate().setDuration(1500).alpha(1f)
        tvSplashScreenDot.animate().setDuration(1500).alpha(1f).withEndAction {
            // Delay for 2 seconds before starting MainActivity
            Handler().postDelayed({
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                //transition animation
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }, 2000) // 2000 milliseconds = 2 seconds
        }
    }
}