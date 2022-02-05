package br.com.dio.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.dio.todolist.R
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.databinding.ActivityDetailBinding
import br.com.dio.todolist.presentation.DetailViewModel
import br.com.dio.todolist.util.formatDate
import br.com.dio.todolist.util.text
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getCategory()
        setListeners()
    }

    private fun getCategory() {
        if (intent.hasCategory("EDIT_TASK")) {
            viewModel.setNewTaskCategory(false)
            setTaskValues()
        } else viewModel.setNewTaskCategory(true)
    }

    private fun setTaskValues() {
        val task = intent.getParcelableExtra<Task>("TASK_TO_EDIT")

        viewModel.taskId = task!!.id

        binding.tilTitle.text = task.title
        binding.tilDescription.text = task.description
        binding.tilDate.text = task.date
        binding.tilHour.text = task.hour
        selectBackgroundColor(task.backgroundColor, false)

    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener { finishActivity() }

        binding.tilDate.editText?.setOnClickListener { pickDate() }
        binding.tilHour.editText?.setOnClickListener { pickHour() }

        binding.btnTransparent.setOnClickListener { selectBackgroundColor(R.color.transparent) }
        binding.btnYellow.setOnClickListener { selectBackgroundColor(R.color.yellow) }
        binding.btnGreen.setOnClickListener { selectBackgroundColor(R.color.green) }
        binding.btnBlue.setOnClickListener { selectBackgroundColor(R.color.blue) }
        binding.btnRed.setOnClickListener { selectBackgroundColor(R.color.red) }

        binding.btnSave.setOnClickListener { saveTask() }
        binding.btnCancel.setOnClickListener { finishActivity() }
    }

    private fun pickDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()

        datePicker.addOnPositiveButtonClickListener {
            val rawOffset = TimeZone.getDefault().rawOffset * -1 //the amount of raw offset time in milliseconds to add or to subtract to UTC.
            binding.tilDate.text = Date(it + rawOffset).formatDate()
        }
        datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
    }

    private fun pickHour() {
        val timerPicker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .build()

        timerPicker.addOnPositiveButtonClickListener {
            binding.tilHour.text = "%02d:%02d".format(timerPicker.hour, timerPicker.minute)
        }
        timerPicker.show(supportFragmentManager, "TIME_PICKER_TAG")
    }

    private fun selectBackgroundColor(color: Int, colorRes: Boolean = true) {
        uncheckAll()

        val colorInt = if (colorRes) getColor(color) else color

        when (colorInt) {
            getColor(R.color.transparent) -> binding.btnTransparent.isChecked = true
            getColor(R.color.yellow) -> binding.btnYellow.isChecked = true
            getColor(R.color.green) -> binding.btnGreen.isChecked = true
            getColor(R.color.blue) -> binding.btnBlue.isChecked = true
            getColor(R.color.red) -> binding.btnRed.isChecked = true
        }

        binding.tilTitle.boxBackgroundColor = colorInt
        binding.tilDescription.boxBackgroundColor = colorInt
        binding.tilDate.boxBackgroundColor = colorInt
        binding.tilHour.boxBackgroundColor = colorInt
    }

    private fun uncheckAll() {
        binding.btnTransparent.isChecked = false
        binding.btnYellow.isChecked = false
        binding.btnGreen.isChecked = false
        binding.btnBlue.isChecked = false
        binding.btnRed.isChecked = false
    }

    private fun saveTask() {
        if (binding.tilTitle.text.isNotBlank() or binding.tilDescription.text.isNotBlank()) {
            val task = Task(
                id = if (!viewModel.isNewTaskCategory()) viewModel.taskId else 0,
                title = binding.tilTitle.text,
                description = binding.tilDescription.text,
                hour = binding.tilHour.text,
                date = binding.tilDate.text,
                backgroundColor = binding.tilTitle.boxBackgroundColor
            )

            if (viewModel.isNewTaskCategory()) viewModel.addTask(task)
            else viewModel.updateTask(task)
            
            finishActivity(true)
        } else {
            Snackbar.make(binding.btnSave, R.string.snackbar_blank_message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.label_ok){}
                .show()
        }
    }

    override fun onBackPressed() {
        finishActivity()
    }

    private fun finishActivity(savedTask: Boolean = false) {

        if (!savedTask) {
            AlertDialog.Builder(this).apply {
                setPositiveButton(getString(R.string.label_yes)){ _, _ ->
                    finish()
                }
                setNegativeButton(getString(R.string.label_no), null)
                setTitle(getString(R.string.dialog_discard_title))
                setMessage(getString(R.string.dialog_discard_message))
            }.show()
        } else {
            Toast.makeText(this, "Tarefa criada!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

}