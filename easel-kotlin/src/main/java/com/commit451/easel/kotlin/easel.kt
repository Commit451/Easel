package com.commit451.easel.kotlin

import android.support.annotation.ColorInt
import android.support.v7.widget.SwitchCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.commit451.easel.Easel
import com.commit451.easel.R

/**
 * Kotlin bindings for Easel
 */

fun EditText.setCursorTint(@ColorInt color: Int) {
    Easel.setCursorTint(this, color)
}

fun EditText.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}

fun CheckBox.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}

fun MenuItem.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}

fun ProgressBar.setTint(@ColorInt color : Int, skipIndeterminate : Boolean = false) {
    Easel.setTint(this, color, skipIndeterminate)
}

fun SwitchCompat.setTint(@ColorInt color : Int, @ColorInt unpressedColor : Int = Easel.getThemeAttrColor(this.context, R.attr.colorSwitchThumbNormal)) {
    Easel.setTint(this, color, unpressedColor)
}

fun RadioButton.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}

fun Button.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}

fun Menu.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}

fun SeekBar.setTint(@ColorInt color : Int) {
    Easel.setTint(this, color)
}


