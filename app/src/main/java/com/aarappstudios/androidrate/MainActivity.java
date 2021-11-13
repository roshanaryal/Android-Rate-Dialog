package com.aarappstudios.androidrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aarappstudios.androidratedialog.AppRate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppRate.with(this).showDialogFromOutside(this);

    }
}