package br.com.dio.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dio.todolist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}