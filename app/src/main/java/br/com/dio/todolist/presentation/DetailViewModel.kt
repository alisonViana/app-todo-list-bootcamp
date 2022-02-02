package br.com.dio.todolist.presentation

import androidx.lifecycle.ViewModel
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.data.repositories.TaskRepository

class DetailViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private var newTaskCategory: Boolean = true

    fun setNewTaskCategory(isNewTask: Boolean) { newTaskCategory = isNewTask }
    fun isNewTaskCategory() = newTaskCategory

    fun addTask(task: Task) {
        taskRepository.insert(task)
    }

}