package com.wojdor.bakingapp.application.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wojdor.bakingapp.application.recipes.RecipesActivity;
import com.wojdor.bakingapp.application.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, RecipesActivity.class));
        finish();
    }
}
