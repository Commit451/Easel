package com.commit451.easel

import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.SwitchCompat
import com.commit451.easel.Easel.getThemeAttrColor

fun SwitchCompat.tint(@ColorInt color: Int, @ColorInt unpressedColor: Int = getThemeAttrColor(context, R.attr.colorSwitchThumbNormal)) {
    val sl = ColorStateList(arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked)), intArrayOf(unpressedColor, color))
    DrawableCompat.setTintList(thumbDrawable, sl)
    DrawableCompat.setTintList(trackDrawable, Easel.createSwitchTrackColorStateList(context, color))
}