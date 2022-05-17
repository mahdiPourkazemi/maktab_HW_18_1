package com.pourkazemi.mahdi.maktab_hw_18_1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    companion object {
        internal const val BASE_URL="http://51.195.19.222:3000/api/v1/"
    }
}