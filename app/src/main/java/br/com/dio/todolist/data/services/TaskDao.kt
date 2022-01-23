package br.com.dio.todolist.data.services

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.dio.todolist.data.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    fun getAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

}