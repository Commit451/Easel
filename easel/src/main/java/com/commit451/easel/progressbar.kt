package com.commit451.easel

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.ColorInt
import android.widget.ProgressBar

fun ProgressBar.tint(@ColorInt color: Int, skipIndeterminate: Boolean = false) {
    val sl = ColorStateList.valueOf(color)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        progressTintList = sl
        secondaryProgressTintList = sl
        if (!skipIndeterminate) {
            indeterminateTintList = sl
        }
    } else {
        var mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            mode = PorterDuff.Mode.MULTIPLY
        }
        if (!skipIndeterminate && indeterminateDrawable != null) {
            indeterminateDrawable.setColorFilter(color, mode)
        }
        if (progressDrawable != null) {
            progressDrawable.setColorFilter(color, mode)
        }
    }
}