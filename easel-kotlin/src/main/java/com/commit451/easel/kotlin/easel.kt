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

fun EditText.tintCursor(@ColorInt color: Int) {
    Easel.tintCursor(this, color)
}

fun EditText.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}

fun CheckBox.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}

fun MenuItem.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}

fun ProgressBar.tint(@ColorInt color : Int, skipIndeterminate : Boolean = false) {
    Easel.tint(this, color, skipIndeterminate)
}

fun SwitchCompat.tint(@ColorInt color : Int, @ColorInt unpressedColor : Int = Easel.getThemeAttrColor(this.context, R.attr.colorSwitchThumbNormal)) {
    Easel.tint(this, color, unpressedColor)
}

fun RadioButton.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}

fun Button.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}

fun Menu.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}

fun SeekBar.tint(@ColorInt color : Int) {
    Easel.tint(this, color)
}


