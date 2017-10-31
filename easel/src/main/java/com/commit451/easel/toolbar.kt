package com.commit451.easel

import android.annotation.SuppressLint
import android.support.annotation.ColorInt
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import java.util.*

/**
 * Sets the color of the overflow menu item within the Toolbar.
 *
 * @param color   color to set the overflow icon to
 * @return true if tint was set.
 */
fun Toolbar.tintOverflow(@ColorInt color: Int): Boolean {
    @SuppressLint("PrivateResource")
    val overflowDescription = context.getString(R.string.abc_action_menu_overflow_description)
    val outViews = ArrayList<View>()
    findViewsWithText(outViews, overflowDescription,
            View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
    if (outViews.isEmpty()) {
        return false
    }
    val overflow = outViews[0] as ImageView
    overflow.setColorFilter(color)
    return true
}