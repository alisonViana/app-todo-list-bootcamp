package br.com.dio.todolist.util

import android.content.Context
import br.com.dio.todolist.R

class GetColorUtil(private val context: Context) {

    fun getColorFromString(color: String): Int {
        return when (color) {
            Colors.Yellow.string -> context.getColor(R.color.yellow)
            Colors.Green.string -> context.getColor(R.color.green)
            Colors.Blue.string -> context.getColor(R.color.blue)
            Colors.Red.string -> context.getColor(R.color.red)
            Colors.Transparent.string -> context.getColor(R.color.transparent)
            else -> context.getColor(R.color.icon_color)
        }
    }
}