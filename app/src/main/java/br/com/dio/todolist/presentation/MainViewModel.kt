package br.com.dio.todolist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.data.repositories.TaskRepository

class MainViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    fun getAllTasks(): LiveData<List<Task>>{
        return taskRepository.getAll()
    }

    fun deleteTask(task: Task) {
        taskRepository.delete(task)
    }
}