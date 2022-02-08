package br.com.dio.todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import br.com.dio.todolist.R
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.databinding.ActivityMainBinding
import br.com.dio.todolist.presentation.MainViewModel
import br.com.dio.todolist.util.Colors
import br.com.dio.todolist.util.GetColorUtil
import br.com.dio.todolist.util.showWithIcons
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val taskListAdapter: TaskListAdapter by inject()
    private val adapter by lazy { taskListAdapter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViews()
        getAllTasks()
        setListeners()
    }

    private fun setViews() {
        binding.rvList.adapter = adapter
        val icon = if (viewModel.filter == "no_filter") R.drawable.ic_no_filter else R.drawable.ic_filter
        val colorFromString = GetColorUtil(this).getColorFromString(viewModel.filter)
        val iconColor = if (viewModel.filter == Colors.Transparent.string) getColor(R.color.icon_color) else colorFromString

        binding.ibMenu.foreground = AppCompatResources.getDrawable(this, icon)
        binding.ibMenu.foreground.setTint(iconColor)
    }

    private fun getAllTasks() {
        viewModel.getAllTasks().observe(this){ taskList ->
            val submitList = if (viewModel.filter == "no_filter") taskList else taskList.filter { task ->
                task.backgroundColor == viewModel.filter
            }
            adapter.submitList(submitList)
        }
    }

    private fun setListeners() {
        binding.ibMenu.setOnClickListener{ view ->
            showFilterMenu(view, R.menu.menu_color_filter)
        }
        binding.fabNewTask.setOnClickListener {
            Intent(this, DetailActivity::class.java).apply {
                addCategory("NEW_TASK")
                startActivity(this)
            }
        }
        adapter.setOnMenuClick = { view, task ->
            showMenu(view, R.menu.menu_task, task)
        }
        adapter.setOnCardClick = { task ->
            editTask(task)
        }

    }

    private fun showFilterMenu(view: View, menu: Int){
        val popupMenu = PopupMenu(applicationContext, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.no_filter -> {
                    applyFilter("no_filter")
                    true
                }
                R.id.color_yellow -> {
                    applyFilter(Colors.Yellow.string)
                    true
                }
                R.id.color_green -> {
                    applyFilter(Colors.Green.string)
                    true
                }
                R.id.color_blue -> {
                    applyFilter(Colors.Blue.string)
                    true
                }
                R.id.color_red -> {
                    applyFilter(Colors.Red.string)
                    true
                }
                R.id.color_transparent -> {
                    applyFilter(Colors.Transparent.string)
                    true
                }
                else-> false
            }
        }

        popupMenu.showWithIcons()
    }

    private fun applyFilter(colorFilter: String) {
        val icon = if (colorFilter == "no_filter") R.drawable.ic_no_filter else R.drawable.ic_filter
        val colorFromString = GetColorUtil(this).getColorFromString(colorFilter)
        val iconColor = if (colorFilter == Colors.Transparent.string) getColor(R.color.icon_color) else colorFromString

        viewModel.filter = colorFilter
        binding.ibMenu.foreground = AppCompatResources.getDrawable(this, icon)
        binding.ibMenu.foreground.setTint(iconColor)

        getAllTasks()
    }


    private fun showMenu(view: View, menu: Int, task: Task) {
        val popupMenu = PopupMenu(applicationContext, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_edit -> {
                    editTask(task)
                    true
                }
                R.id.menu_item_delete -> {
                    deleteTask(task)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun editTask(task: Task) {
        Intent(this, DetailActivity::class.java).apply {
            addCategory("EDIT_TASK")
            putExtra("TASK_TO_EDIT", task)
            startActivity(this)
        }
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