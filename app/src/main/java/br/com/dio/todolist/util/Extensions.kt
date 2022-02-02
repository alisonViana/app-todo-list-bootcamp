package br.com.dio.todolist.util

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

var TextInputLayout.text: String
    get() = editText?.text.toString()
    set(value) {
        editText?.setText(value)
    }

fun Date.formatDate(): String {
    val language = Locale.getDefault().language
    val country = Locale.getDefault().country
    val locale = Locale(language, country)
    val pattern = if (country == "BR") "dd/MM/yyyy" else "yyyy/MM/dd"

    return SimpleDateFormat(pattern, locale).format(this)
}
