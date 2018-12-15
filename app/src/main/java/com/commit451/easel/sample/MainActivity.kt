package com.commit451.easel.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.*
import com.commit451.easel.tint
import com.commit451.easel.tintEdgeEffect
import com.commit451.easel.tintOverflow


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Easel"
        toolbar.inflateMenu(R.menu.menu_main)
        val searchMenuItem = toolbar.menu.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView

        toolbar.menu.tint(Color.MAGENTA)
        toolbar.tintOverflow(Color.MAGENTA)

        val scrollView = findViewById<View>(R.id.scrollview) as ScrollView
        scrollView.tintEdgeEffect(Color.MAGENTA)

        val editText = findViewById<View>(R.id.editText) as EditText
        val radioButton = findViewById<View>(R.id.radioButton) as RadioButton
        val checkBox = findViewById<View>(R.id.checkBox) as CheckBox
        val switchCompat = findViewById<View>(R.id.switchCompat) as SwitchCompat
        val progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        val seekBar = findViewById<View>(R.id.seekBar) as SeekBar
        val button = findViewById<View>(R.id.button) as Button
        val spinner = findViewById<View>(R.id.spinner) as Spinner
        editText.tint(Color.MAGENTA)
        radioButton.tint(Color.MAGENTA)
        checkBox.tint(Color.MAGENTA)
        switchCompat.tint(Color.MAGENTA)
        progressBar.tint(Color.MAGENTA)
        seekBar.tint(Color.MAGENTA)
        button.tint(Color.MAGENTA)
        spinner.tint(Color.MAGENTA)
    }
}
