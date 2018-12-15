package com.commit451.easel

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import android.widget.SeekBar

/**
 * Tint the [SeekBar]
 *
 * @param color   the color
 */
fun SeekBar.tint(@ColorInt color: Int) {
    val s1 = ColorStateList.valueOf(color)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.thumbTintList = s1
        this.progressTintList = s1
    } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
        val progressDrawable = DrawableCompat.wrap(this.progressDrawable)
        this.progressDrawable = progressDrawable
        DrawableCompat.setTintList(progressDrawable, s1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val thumbDrawable = DrawableCompat.wrap(this.thumb)
            DrawableCompat.setTintList(thumbDrawable, s1)
            this.thumb = thumbDrawable
        }
    } else {
        var mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            mode = PorterDuff.Mode.MULTIPLY
        }
        if (this.indeterminateDrawable != null)
            this.indeterminateDrawable.setColorFilter(color, mode)
        if (this.progressDrawable != null)
            this.progressDrawable.setColorFilter(color, mode)
    }
}