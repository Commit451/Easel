package com.commit451.easel.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.commit451.easel.kotlin.setCursorTint

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        var editText = findViewById(R.id.editText) as EditText
        editText.setCursorTint(Color.RED)
    }
}
