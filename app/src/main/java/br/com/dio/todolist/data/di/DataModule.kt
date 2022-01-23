package br.com.dio.todolist.data.di

import br.com.dio.todolist.data.database.TaskDatabase
import br.com.dio.todolist.data.repositories.TaskRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

    fun load(){
        loadKoinModules(repositoriesModules()
        + daoModules())
    }

    // single - o koin devolve sempre a mesma instância
    // factory - o koin devolve uma instância nova a cada chamada

    private fun repositoriesModules(): Module {
        return module {
            single { TaskRepository(get()) }
        }
    }

    private fun daoModules(): Module {
        return module {
            single { TaskDatabase.getInstance(androidContext()).taskDao}
        }
    }
}