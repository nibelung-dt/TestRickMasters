package com.tarasov.testrickmasters

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

//import io.realm.gradle.Realm



@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmName = "My Project"
        val config = RealmConfiguration.Builder()
            .name(realmName)
            .build()
        val uiThreadRealm = Realm.getInstance(config)
        //addChangeListenerToRealm(uiThreadRealm)

    }
}