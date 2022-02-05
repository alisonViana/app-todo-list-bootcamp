package br.com.dio.todolist.data.repositories

import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.data.services.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

    /*
    fun getAll() = runBlocking {
        dao.getAll()
    }


    fun insert(task: Task) = runBlocking {
        launch(Dispatchers.IO) {
            try {
                dao.insert(task)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun delete(task: Task) = runBlocking {
        launch(Dispatchers.IO) {
            try {
                dao.delete(task)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun update(task: Task) = runBlocking {
        launch(Dispatchers.IO) {  }
    }
     */

}