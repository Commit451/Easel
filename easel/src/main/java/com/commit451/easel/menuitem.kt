package com.commit451.easel

import android.graphics.PorterDuff
import android.support.annotation.ColorInt
import android.view.MenuItem

/**
 * Tint a menu item
 *
 * @param color    the color
 */
fun MenuItem.tint(@ColorInt color: Int) {
    val icon = icon
    if (icon != null) {
        icon.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    } else {
        throw IllegalArgumentException("Menu item does not have an icon")
    }
}