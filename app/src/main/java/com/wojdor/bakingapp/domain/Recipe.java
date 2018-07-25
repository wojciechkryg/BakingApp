package com.wojdor.bakingapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import static com.wojdor.bakingapp.application.utils.Constants.BIG_DOT;
import static com.wojdor.bakingapp.application.utils.Constants.EOL;

public class Recipe implements Parcelable {

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private int servings;
    private String imageUrl;

    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, int servings, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.imageUrl = imageUrl;
    }

    private Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
        servings = in.readInt();
        imageUrl = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(servings);
        dest.writeString(imageUrl);
    }

    public String getFormattedIngredients() {
        StringBuilder formattedIngredients = new StringBuilder();
        Ingredient ingredient;
        for (int i = 0; i < getIngredients().size(); i++) {
            ingredient = getIngredients().get(i);
            formattedIngredients.append(String.format("%s %s", BIG_DOT, ingredient.toString()));
            if (i < getIngredients().size() - 1) {
                formattedIngredients.append(EOL);
            }
        }
        return formattedIngredients.toString();
    }
}
