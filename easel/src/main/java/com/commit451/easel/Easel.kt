package com.commit451.easel

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

/**
 * Color things!
 */
object Easel {

    /**
     * Get a darker version of the color based on the ratio
     *
     * @param color        starting color
     * @param darkerAmount value between 0 and 1 to darken the color by
     * @return darker color
     */
    @JvmOverloads
    @ColorInt
    fun darkerColor(@ColorInt color: Int, darkerAmount: Float = 0.9f): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= darkerAmount
        return Color.HSVToColor(hsv)
    }

    /**
     * Gets the color with the alpha changed by a factor.
     *
     * @param color  original color
     * @param factor factor, such as 0.5f for 50%
     * @return the color with the adjusted alpha
     */
    fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
        return Color.argb(Math.round(Color.alpha(color) * factor), Color.red(color), Color.green(color), Color.blue(color))
    }

    /**
     * Returns true if the color is dark, false if not
     */
    fun isColorDark(@ColorInt color: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return darkness >= 0.5
    }

    /**
     * Get a color from the attribute theme
     *
     * @param context        theme context
     * @param attributeColor the attribute color, ex R.attr.colorPrimary
     * @return the color, or [Color.TRANSPARENT] if failed to resolve
     */
    internal fun getThemeAttrColor(context: Context, @AttrRes attributeColor: Int): Int {
        val attrs = intArrayOf(attributeColor)
        val ta = context.obtainStyledAttributes(attrs)
        val color = ta.getColor(0, Color.TRANSPARENT)
        ta.recycle()
        return color
    }

    internal fun createEditTextColorStateList(context: Context, @ColorInt color: Int): ColorStateList {
        val states = arrayOfNulls<IntArray>(3)
        val colors = IntArray(3)
        var i = 0
        states[i] = intArrayOf(-android.R.attr.state_enabled)
        colors[i] = getThemeAttrColor(context, R.attr.colorControlNormal)
        i++
        states[i] = intArrayOf(-android.R.attr.state_pressed, -android.R.attr.state_focused)
        colors[i] = getThemeAttrColor(context, R.attr.colorControlNormal)
        i++
        states[i] = intArrayOf()
        colors[i] = color
        return ColorStateList(states, colors)
    }

    @ColorInt
    internal fun getDisabledColor(context: Context): Int {
        val primaryColor = getThemeAttrColor(context, android.R.attr.textColorPrimary)
        val disabledColor = if (isColorDark(primaryColor)) Color.BLACK else Color.WHITE
        return adjustAlpha(disabledColor, 0.3f)
    }

    internal fun createSwitchTrackColorStateList(context: Context, @ColorInt color: Int): ColorStateList {
        val states = arrayOfNulls<IntArray>(3)
        val colors = IntArray(3)
        var i = 0

        // Disabled state
        states[i] = intArrayOf(-android.R.attr.state_enabled)
        colors[i] = adjustAlpha(getThemeAttrColor(context, R.attr.colorControlNormal), 0.1f)
        i++

        states[i] = intArrayOf(android.R.attr.state_checked)
        colors[i] = adjustAlpha(color, 0.3f)
        i++

        // Default enabled state
        states[i] = IntArray(0)
        colors[i] = adjustAlpha(getThemeAttrColor(context, R.attr.colorControlNormal), 0.5f)

        return ColorStateList(states, colors)
    }
}
