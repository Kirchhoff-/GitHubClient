package com.kirchhoff.example.githubclient.extensions

import android.view.View
import android.widget.EditText

/**
 * @author Kirchhoff-
 */

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}


fun EditText.getString(): String = this.text.toString()
