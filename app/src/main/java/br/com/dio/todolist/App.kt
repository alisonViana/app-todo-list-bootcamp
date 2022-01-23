package br.com.dio.todolist

import android.app.Application
import br.com.dio.todolist.data.di.DataModule
import br.com.dio.todolist.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        PresentationModule.load()

    }

}