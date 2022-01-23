package br.com.dio.todolist.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import br.com.dio.todolist.R
import br.com.dio.todolist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener { finishActivity() }

        binding.tilDate.setOnClickListener {  }
        binding.tilHour.setOnClickListener {  }

        binding.btnTransparent.setOnClickListener { selectBackgroundColor(R.color.transparent) }
        binding.btnYellow.setOnClickListener { selectBackgroundColor(R.color.yellow) }
        binding.btnGreen.setOnClickListener { selectBackgroundColor(R.color.green) }
        binding.btnBlue.setOnClickListener { selectBackgroundColor(R.color.blue) }
        binding.btnRed.setOnClickListener { selectBackgroundColor(R.color.red) }

        binding.btnSave.setOnClickListener {  }
        binding.btnCancel.setOnClickListener { finishActivity() }
    }

    private fun finishActivity() {
        AlertDialog.Builder(this).apply {
            setPositiveButton(getString(R.string.dialog_positive)){_, _ ->
                finish()
            }
            setNegativeButton(getString(R.string.dialog_negative), null)
            setTitle(getString(R.string.dialog_title))
            setMessage(getString(R.string.dialog_message))
        }.show()
    }

    private fun selectBackgroundColor(color: Int) {
        uncheckAll()
        when (color) {
            R.color.transparent -> binding.btnTransparent.isChecked = true
            R.color.yellow -> binding.btnYellow.isChecked = true
            R.color.green -> binding.btnGreen.isChecked = true
            R.color.blue -> binding.btnBlue.isChecked = true
            R.color.red -> binding.btnRed.isChecked = true
        }
        binding.tilTitle.boxBackgroundColor = getColor(color)
        binding.tilDescription.boxBackgroundColor = getColor(color)
        binding.tilDate.boxBackgroundColor = getColor(color)
        binding.tilHour.boxBackgroundColor = getColor(color)
    }

    private fun uncheckAll() {
        binding.btnTransparent.isChecked = false
        binding.btnYellow.isChecked = false
        binding.btnGreen.isChecked = false
        binding.btnBlue.isChecked = false
        binding.btnRed.isChecked = false
    }

}