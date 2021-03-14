package com.ridhoafni.kitabisamoviedb.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ridhoafni.kitabisamoviedb.MainActivity
import com.ridhoafni.kitabisamoviedb.R

class SplashScreenActivity : AppCompatActivity() {
    private var delay: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }
}