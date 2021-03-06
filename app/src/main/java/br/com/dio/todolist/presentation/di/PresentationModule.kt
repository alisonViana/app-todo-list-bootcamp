package br.com.dio.todolist.presentation.di

import br.com.dio.todolist.presentation.DetailViewModel
import br.com.dio.todolist.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModules())
    }

    private fun viewModelModules(): Module {
        return module {
            viewModel { MainViewModel(get()) }
            viewModel { DetailViewModel(get()) }
        }
    }
}