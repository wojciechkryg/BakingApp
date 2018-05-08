package com.wojdor.bakingapp.application.base;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(@IdRes int resId, BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(resId, fragment)
                .commit();
    }
}
