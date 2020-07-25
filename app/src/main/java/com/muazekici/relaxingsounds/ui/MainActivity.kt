package com.muazekici.relaxingsounds.ui

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.RelaxingSoundsApplication
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.CategoriesRepository
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
