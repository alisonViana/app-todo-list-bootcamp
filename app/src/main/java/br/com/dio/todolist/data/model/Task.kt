package br.com.dio.todolist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.StringBufferInputStream

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val hour: String,
    val backgroundColor: Int
)
