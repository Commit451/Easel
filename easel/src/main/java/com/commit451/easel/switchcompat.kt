package com.commit451.easel

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import androidx.appcompat.widget.SwitchCompat
import com.commit451.easel.Easel.getThemeAttrColor

fun SwitchCompat.tint(@ColorInt color: Int, @ColorInt unpressedColor: Int = getThemeAttrColor(context, R.attr.colorSwitchThumbNormal)) {
    val sl = ColorStateList(arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked)), intArrayOf(unpressedColor, color))
    DrawableCompat.setTintList(thumbDrawable, sl)
    DrawableCompat.setTintList(trackDrawable, Easel.createSwitchTrackColorStateList(context, color))
}