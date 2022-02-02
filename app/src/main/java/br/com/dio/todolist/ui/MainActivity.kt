package br.com.dio.todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dio.todolist.databinding.ActivityMainBinding
import br.com.dio.todolist.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvList.adapter = adapter

        getAllTasks()
        setListeners()
    }

    private fun getAllTasks() {
        viewModel.getAllTasks().observe(this){ taskList ->
            adapter.submitList(taskList)
        }
    }

    private fun setListeners() {
        binding.fabNewTask.setOnClickListener {
            Intent(this, DetailActivity::class.java).apply {
                addCategory("NEW_TASK")
                startActivity(this)
            }
        }
    }
}