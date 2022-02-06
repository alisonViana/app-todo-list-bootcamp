package br.com.dio.todolist

import android.app.Application
import br.com.dio.todolist.data.di.DataModule
import br.com.dio.todolist.presentation.di.PresentationModule
import br.com.dio.todolist.util.GetColorUtil
import br.com.dio.todolist.util.di.UtilModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    val getColorUtil: GetColorUtil by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        PresentationModule.load()
        UtilModule.load()

    }

}