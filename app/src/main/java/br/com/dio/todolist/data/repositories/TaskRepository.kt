package br.com.dio.todolist.data.repositories

import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.data.services.TaskDao

class TaskRepository(private val dao: TaskDao) {

    fun getAll() = dao.getAll()

    suspend fun insert(task: Task) {
        dao.insert(task)
    }

    suspend fun delete(task: Task) {
        dao.delete(task)
    }

    suspend fun update(task: Task) {
        dao.update(task)
    }
}