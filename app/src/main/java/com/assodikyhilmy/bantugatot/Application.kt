package com.assodikyhilmy.bantugatot

import android.app.Application

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}