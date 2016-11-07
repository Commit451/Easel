package com.commit451.easel.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SeekBar;

import com.commit451.easel.Easel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Easel");
        toolbar.inflateMenu(R.menu.menu_main);
        Easel.tint(toolbar.getMenu(), Color.MAGENTA);
        Easel.tintOverflow(toolbar, Color.MAGENTA);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview);
        Easel.tintEdgeEffect(scrollView, Color.MAGENTA);

        EditText editText = (EditText) findViewById(R.id.editText);
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.switchCompat);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        Button button = (Button) findViewById(R.id.button);
        Easel.tint(editText, Color.MAGENTA);
        Easel.tint(radioButton, Color.MAGENTA);
        Easel.tint(checkBox, Color.MAGENTA);
        Easel.tint(switchCompat, Color.MAGENTA);
        Easel.tint(progressBar, Color.MAGENTA);
        Easel.tint(seekBar, Color.MAGENTA);
        Easel.tint(button, Color.MAGENTA);
        findViewById(R.id.button_kotlin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KotlinActivity.class));
            }
        });
    }
}
