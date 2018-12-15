package com.commit451.easel

import android.graphics.PorterDuff
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import android.widget.TextView
import java.lang.reflect.Field

/**
 * Reflection is fun
 */
internal object ReflectionUtil {

    fun getField(clazz: Class<*>, fieldName: String): Field {
        val field = clazz.getDeclaredField(fieldName)
        field.isAccessible = true
        return field
    }

    fun getEditorField(): Field {
        val fEditor = TextView::class.java.getDeclaredField("mEditor")
        fEditor.isAccessible = true
        return fEditor
    }

    fun setDrawable(textView: TextView, editor: Any, field: Field, @ColorInt color: Int,
                            @DrawableRes drawableRes: Int) {
        val drawable = ContextCompat.getDrawable(textView.context, drawableRes)
                ?.mutate()
        drawable?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        field.set(editor, drawable)
    }
}
