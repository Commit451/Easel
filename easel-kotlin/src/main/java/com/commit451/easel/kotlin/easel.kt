package com.commit451.easel.kotlin

import android.support.annotation.ColorInt
import android.widget.EditText
import com.commit451.easel.Easel

/**
 * Kotlin bindings for Easel
 */

fun EditText.setCursorTint(@ColorInt color: Int) {
    Easel.setCursorTint(this, color)
}

fun EditText.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}
