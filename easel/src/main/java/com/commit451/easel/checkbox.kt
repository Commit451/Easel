package com.commit451.easel

import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.widget.CheckBox
import com.commit451.easel.Easel.getThemeAttrColor

fun CheckBox.tint(@ColorInt color: Int) {
    val disabledColor = Easel.getDisabledColor(getContext())
    val sl = ColorStateList(arrayOf(intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked), intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked), intArrayOf(-android.R.attr.state_enabled, -android.R.attr.state_checked), intArrayOf(-android.R.attr.state_enabled, android.R.attr.state_checked)), intArrayOf(getThemeAttrColor(getContext(), R.attr.colorControlNormal), color, disabledColor, disabledColor))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setButtonTintList(sl)
    } else {
        val checkDrawable = ContextCompat.getDrawable(getContext(), R.drawable.abc_btn_check_material)
        val drawable = DrawableCompat.wrap(checkDrawable)
        DrawableCompat.setTintList(drawable, sl)
        setButtonDrawable(drawable)
    }
}
