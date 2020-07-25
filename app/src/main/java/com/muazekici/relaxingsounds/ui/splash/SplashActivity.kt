package com.muazekici.relaxingsounds.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.main.MainActivity
import com.muazekici.relaxingsounds.ui.utils.startActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            delay(DELAY_IN_MS)
            (this@SplashActivity as Context).startActivity<MainActivity> { }
        }
    }

    companion object {
        private const val DELAY_IN_MS = 2000L
    }
}