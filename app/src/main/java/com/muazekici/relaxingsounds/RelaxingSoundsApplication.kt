package com.muazekici.relaxingsounds

import android.app.Application
import com.muazekici.relaxingsounds.di.AppComponent
import com.muazekici.relaxingsounds.di.DaggerAppComponent
import timber.log.Timber

class RelaxingSoundsApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.injectApplication(this)
    }
}