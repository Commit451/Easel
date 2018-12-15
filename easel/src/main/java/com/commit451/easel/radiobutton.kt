package com.commit451.easel

import android.content.res.ColorStateList
import android.os.Build
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import android.widget.RadioButton
import com.commit451.easel.Easel.getThemeAttrColor

/**
 * Tint the radio button
 * @param color the color
 */
fun RadioButton.tint(@ColorInt color: Int) {
    val disabledColor = Easel.getDisabledColor(context)
    val sl = ColorStateList(arrayOf(intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked), intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked), intArrayOf(-android.R.attr.state_enabled, -android.R.attr.state_checked), intArrayOf(-android.R.attr.state_enabled, android.R.attr.state_checked)), intArrayOf(getThemeAttrColor(context, R.attr.colorControlNormal), color, disabledColor, disabledColor))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        buttonTintList = sl
    } else {
        val radioDrawable = ContextCompat.getDrawable(context, R.drawable.abc_btn_radio_material)!!
        val d = DrawableCompat.wrap(radioDrawable)
        DrawableCompat.setTintList(d, sl)
        buttonDrawable = d
    }
}