package br.com.dio.todolist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.data.repositories.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    var filter = "no_filter"

    fun getAllTasks(): LiveData<List<Task>>{
        return taskRepository.getAll().asLiveData()
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.delete(task)
    }
}