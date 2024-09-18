package com.vilvanetworks.ayyawinstudyidp.app

import android.app.Application
import android.content.Context
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetComposeLogin : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefHelper.sharedPreferences = getSharedPreferences(SharedPrefKey.applicationName, Context.MODE_PRIVATE)
//        PrefHelper().getIntFromShared(SharedPrefKey.appName)
    }
}