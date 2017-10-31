package com.commit451.easel

import android.support.annotation.ColorInt
import android.view.Menu

/**
 * Tint all menu items within a menu to be a certain color. Note that this does not tint the
 * overflow menu. Call tintOverflow for that.
 *
 * @param color the color
 */
fun Menu.tint(@ColorInt color: Int) {
    for (i in 0 until size()) {
        val menuItem = getItem(i)
        if (menuItem.icon != null) {
            menuItem.tint(color)
        }
    }
}