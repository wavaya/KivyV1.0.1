package xyz.wayhua.kivy101

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import xyz.wayhua.kivy101.di.allModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()

     startKoin{
         androidLogger(level= Level.DEBUG)
         androidContext(this@App)
         modules(allModules)
     }
    }
}