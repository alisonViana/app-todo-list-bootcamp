package br.com.dio.todolist.util.di

import br.com.dio.todolist.util.GetColorUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object UtilModule {

    fun load(){
        loadKoinModules(colorUtilModules())
    }

    private fun colorUtilModules(): Module {
        return module {
            factory {
                GetColorUtil(androidContext())
            }
        }
    }
}