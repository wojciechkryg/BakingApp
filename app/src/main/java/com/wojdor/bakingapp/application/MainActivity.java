package com.wojdor.bakingapp.application;

import android.os.Bundle;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
