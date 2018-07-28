package com.wojdor.bakingapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wojdor.bakingapp.application.recipedetails.RecipeDetailsActivity;
import com.wojdor.bakingapp.application.utils.Extras;
import com.wojdor.bakingapp.mockdata.MockData;
import com.wojdor.bakingapp.testui.StepsFragmentTestView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> mainActivityActivityTestRule =
            new ActivityTestRule<RecipeDetailsActivity>(RecipeDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra(Extras.RECIPE_EXTRA, MockData.getRecipe());
                    return intent;
                }
            };

    @Test
    public void ingredients_ShouldDisplay() {
        new StepsFragmentTestView().checkIngredientsShow();
    }

    @Test
    public void ingredients_ShouldShowInCorrectFormat() {
        new StepsFragmentTestView().checkIngredientsShowInCorrectFormat();
    }
}
