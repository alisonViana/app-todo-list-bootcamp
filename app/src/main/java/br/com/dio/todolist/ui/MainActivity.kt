package br.com.dio.todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import br.com.dio.todolist.R
import br.com.dio.todolist.data.model.Task
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
        adapter.setOnMenuClick = { view, task ->
            showMenu(view, R.menu.menu_task, task)
        }

    }

    private fun showMenu(view: View, menuTask: Int, task: Task) {
        val popupMenu = PopupMenu(applicationContext, view)
        popupMenu.menuInflater.inflate(menuTask, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_item_edit -> {
                    Log.i("myTag", "Click: Edit")
                    true
                }
                R.id.menu_item_delete -> {
                    deleteTask(task)
                    true
                }
                else -> super.onContextItemSelected(item)
            }
        }
        popupMenu.show()

    }

    private fun deleteTask(task: Task) {
        AlertDialog.Builder(this).apply {
            setPositiveButton(R.string.label_yes) { _, _ ->
                viewModel.deleteTask(task)
            }
            setNegativeButton(R.string.label_no, null)
            setTitle(R.string.label_delete)
            setMessage(R.string.dialog_delete_message)
        }.show()
    }
}