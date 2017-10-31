package com.commit451.easel

import android.graphics.PorterDuff
import android.support.annotation.ColorInt
import android.widget.Spinner

fun Spinner.tint(@ColorInt color: Int) {
    background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}