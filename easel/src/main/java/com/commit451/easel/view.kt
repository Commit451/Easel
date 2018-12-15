package com.commit451.easel

import android.annotation.TargetApi
import androidx.annotation.ColorInt
import android.view.View
import android.widget.EdgeEffect

/**
 * Tint the edge effect when you reach the end of a scroll view. API 21+ only. This works on scrollable views
 *
 * @param color          the color
 * @return true if it worked, false if it did not
 */
@TargetApi(21)
fun View.tintEdgeEffect(@ColorInt color: Int): Boolean {
    //http://stackoverflow.com/questions/27104521/android-lollipop-scrollview-edge-effect-color
    var outcome = false
    val edgeGlows = arrayOf("mEdgeGlowTop", "mEdgeGlowBottom", "mEdgeGlowLeft", "mEdgeGlowRight")
    for (edgeGlow in edgeGlows) {
        var clazz: Class<*>? = this.javaClass
        while (clazz != null) {
            try {
                val edgeGlowField = clazz.getDeclaredField(edgeGlow)
                edgeGlowField.isAccessible = true
                val edgeEffect = edgeGlowField.get(this) as EdgeEffect
                edgeEffect.color = color
                outcome = true
                break
            } catch (e: Exception) {
                clazz = clazz.superclass
            }

        }
    }
    return outcome
}