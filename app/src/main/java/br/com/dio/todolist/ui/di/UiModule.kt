package br.com.dio.todolist.ui.di

import br.com.dio.todolist.ui.TaskListAdapter
import br.com.dio.todolist.util.GetColorUtil
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object UiModule {

    fun load(){
        loadKoinModules(listAdapterModules())
    }

    private fun listAdapterModules(): Module {
        return module {
            factory {
                TaskListAdapter(get())
            }
        }
    }
}