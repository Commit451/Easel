package com.commit451.easel

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import android.widget.Button

/**
 * Tint the button
 *
 * @param color  the color
 */
fun Button.tint(@ColorInt color: Int) {
    val sl = ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))
    ViewCompat.setBackgroundTintList(this, sl)
}
