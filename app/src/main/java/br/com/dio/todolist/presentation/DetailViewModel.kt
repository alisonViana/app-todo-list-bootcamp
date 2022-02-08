package br.com.dio.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.data.repositories.TaskRepository
import br.com.dio.todolist.data.model.Colors
import kotlinx.coroutines.launch

class DetailViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private var newTaskCategory: Boolean = true
    var taskId: Int = 0
    var backgroundColor: String = Colors.Transparent.string

    fun setNewTaskCategory(isNewTask: Boolean) { newTaskCategory = isNewTask }
    fun isNewTaskCategory() = newTaskCategory

    fun addTask(task: Task) = viewModelScope.launch {
        taskRepository.insert(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.update(task)
    }

}