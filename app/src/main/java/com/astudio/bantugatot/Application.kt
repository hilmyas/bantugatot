package com.astudio.bantugatot

import android.app.Application
import com.google.firebase.FirebaseApp
import timber.log.Timber

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}