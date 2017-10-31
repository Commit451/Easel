package com.commit451.easel

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatEditText
import android.widget.EditText
import android.widget.TextView

@SuppressLint("RestrictedApi")
fun EditText.tint(@ColorInt color : Int) {
    val editTextColorStateList = Easel.createEditTextColorStateList(this.getContext(), color)
    if (this is AppCompatEditText) {
        supportBackgroundTintList = editTextColorStateList
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setBackgroundTintList(editTextColorStateList)
    }
    (this as TextView).tint(color)
}
