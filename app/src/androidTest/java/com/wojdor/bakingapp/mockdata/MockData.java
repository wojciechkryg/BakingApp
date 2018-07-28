package com.wojdor.bakingapp.mockdata;

import com.wojdor.bakingapp.domain.Ingredient;
import com.wojdor.bakingapp.domain.Recipe;
import com.wojdor.bakingapp.domain.Step;

import java.util.ArrayList;
import java.util.List;

public final class MockData {

    static public Recipe getRecipe() {
        return new Recipe(
                1,
                "Apple Pie",
                getMockIngredients(),
                getMockSteps(),
                4,
                ""
        );
    }

    static private List<Ingredient> getMockIngredients() {
        return new ArrayList<Ingredient>() {{
            add(new Ingredient(1, "G", "sugar"));
            add(new Ingredient(2, "G", "chocolate"));
            add(new Ingredient(3.5, "UNIT", "apple"));
        }};
    }

    static private List<Step> getMockSteps() {
        return new ArrayList<Step>() {{
            add(new Step(1, "Preparation1", "Prepare ingredients1", "", ""));
            add(new Step(1, "Preparation2", "Prepare ingredients2", "", ""));
            add(new Step(1, "Preparation3", "Prepare ingredients3", "", ""));
            add(new Step(1, "Preparation4", "Prepare ingredients4", "", ""));
        }};
    }
}
