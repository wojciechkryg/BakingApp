package com.wojdor.bakingapp.application.base;

import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setTitle(title);
    }

    protected void replaceFragment(@IdRes int resId, BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(resId, fragment)
                .commit();
    }

    protected boolean isFragmentNotAddedToLayout(@IdRes int resId) {
        return getSupportFragmentManager().findFragmentById(resId) == null;
    }
}
