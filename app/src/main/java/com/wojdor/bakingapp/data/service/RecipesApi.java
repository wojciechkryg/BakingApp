package com.wojdor.bakingapp.data.service;

import com.wojdor.bakingapp.data.model.RecipeModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipesApi {

    @GET("baking.json")
    Observable<List<RecipeModel>> getRecipes();
}
