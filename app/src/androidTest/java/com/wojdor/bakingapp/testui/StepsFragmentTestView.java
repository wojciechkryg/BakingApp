package com.wojdor.bakingapp.testui;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.domain.Recipe;
import com.wojdor.bakingapp.mockdata.MockData;

public class StepsFragmentTestView extends BaseTestView {

    public void checkIngredientsShow() {
        Recipe recipe = MockData.getRecipe();
        viewWithTextIsDisplayed(recipe.getFormattedIngredients());
    }

    public void checkIngredientsShowInCorrectFormat() {
        Recipe recipe = MockData.getRecipe();
        viewHasText(R.id.fragment_steps_ingredients_tv, recipe.getFormattedIngredients());
    }
}
