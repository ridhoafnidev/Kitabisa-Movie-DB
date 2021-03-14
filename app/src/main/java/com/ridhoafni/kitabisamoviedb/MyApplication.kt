@file:Suppress("unused")

package com.ridhoafni.kitabisamoviedb

import android.app.Application
import com.ridhoafni.core.di.databaseModule
import com.ridhoafni.core.di.networkModule
import com.ridhoafni.core.di.repositoryModule
import com.ridhoafni.core.utils.ReleaseTree
import com.ridhoafni.kitabisamoviedb.di.useCaseModule
import com.ridhoafni.kitabisamoviedb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }else{
            Timber.plant(ReleaseTree())
        }
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}