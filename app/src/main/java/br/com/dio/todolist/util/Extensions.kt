package br.com.dio.todolist.util

import android.annotation.SuppressLint
import android.util.Log
import android.widget.PopupMenu
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
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

@SuppressLint("DiscouragedPrivateApi")
fun PopupMenu.showWithIcons() {
    try {
        val fieldMPopupMenu = PopupMenu::class.java.getDeclaredField("mPopup")
        fieldMPopupMenu.isAccessible = true
        val mPopupMenu = fieldMPopupMenu.get(this)
        mPopupMenu.javaClass
            .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(mPopupMenu, true)

    } catch (ex: Exception) {
        Log.e("myTag", "Error showing menu icons.", ex)
    } finally {
        this.show()
    }
}