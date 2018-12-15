package com.commit451.easel

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import android.widget.TextView
import com.commit451.easel.ReflectionUtil.getEditorField
import com.commit451.easel.ReflectionUtil.getField

/**
 * Tint the textview. This is a convenience call through to {@link #tintCursor(TextView, int)}, {@link #tintHandles(TextView, int)}
 * and {@link #tintSelectionHighlight(TextView, int)}
 *
 * @param color    the color
 */
fun TextView.tint(@ColorInt color: Int) {
    tintCursor(color)
    tintHandles(color)
    tintSelectionHighlight(color)
}

/**
 * Tints the cursor color of the EditText. Kinda hacky using reflection, but it gets the job done.
 * http://stackoverflow.com/questions/25996032/how-to-change-programatically-edittext-cursor-color-in-android
 *
 * @param color    color to change the cursor to
 * @return true if cursor was successfully tinted, false if Android has changed the field names and reflection has failed us
 */
fun TextView.tintCursor(@ColorInt color: Int): Boolean {
    try {
        val fCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        fCursorDrawableRes.isAccessible = true
        val mCursorDrawableRes = fCursorDrawableRes.getInt(this)
        val fEditor = ReflectionUtil.getEditorField()
        val editor = fEditor.get(this)
        val clazz = editor.javaClass
        val fCursorDrawable = clazz.getDeclaredField("mCursorDrawable")
        fCursorDrawable.isAccessible = true
        val drawables = arrayOfNulls<Drawable>(2)
        drawables[0] = ContextCompat.getDrawable(this.context, mCursorDrawableRes)
        drawables[0]?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        drawables[1] = ContextCompat.getDrawable(this.context, mCursorDrawableRes)
        drawables[1]?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        fCursorDrawable.set(editor, drawables)
    } catch (e: Exception) {
        return false
    }
    return true
}

/**
 * Tint the handles that appear when you select text
 *
 * @param color    the color
 * @return true if properly tinted, false if reflection has failed us
 */
fun TextView.tintHandles(@ColorInt color: Int): Boolean {
    try {
        val fTextSelectHandleLeftRes = getField(TextView::class.java, "mTextSelectHandleLeftRes")
        val fTextSelectHandleRightRes = getField(TextView::class.java, "mTextSelectHandleRightRes")
        val fTextSelectHandleCenterRes = getField(TextView::class.java, "mTextSelectHandleRes")
        val leftRes = fTextSelectHandleLeftRes.getInt(this)
        val rightRes = fTextSelectHandleRightRes.getInt(this)
        val centerRes = fTextSelectHandleCenterRes.getInt(this)
        val fEditor = getEditorField()
        val editor = fEditor.get(this)
        val clazz = editor.javaClass
        val fHandleLeftDrawable = getField(clazz, "mSelectHandleLeft")
        val fHandleRightDrawable = getField(clazz, "mSelectHandleRight")
        val fHandleCenterDrawable = getField(clazz, "mSelectHandleCenter")
        ReflectionUtil.setDrawable(this, editor, fHandleLeftDrawable, color, leftRes)
        ReflectionUtil.setDrawable(this, editor, fHandleRightDrawable, color, rightRes)
        ReflectionUtil.setDrawable(this, editor, fHandleCenterDrawable, color, centerRes)
    } catch (e: Exception) {
        return false
    }

    return true
}

/**
 * Tint the highlight that appears when you select text
 *
 * @param color    the color to tint to
 * @return true if success, false if reflection failed
 */
fun TextView.tintSelectionHighlight(@ColorInt color: Int): Boolean {
    //You would think you would modify mHighlightPaint, but no, you need to modify mHighlightColor,
    //as it gets set as the color on the paint on each draw call
    try {
        val fHighlightColor = ReflectionUtil.getField(TextView::class.java, "mHighlightColor")
        fHighlightColor.set(this, color)
    } catch (e: Exception) {
        return false
    }

    return true
}