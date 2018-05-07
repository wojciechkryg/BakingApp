package com.wojdor.bakingapp.data.service;

public final class RecipesService {

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private static RecipesApi instance;

    private RecipesService() {
    }

    public static RecipesApi getInstance() {
        if (instance == null) {
            instance = ServiceGenerator.getInstance(BASE_URL).createService(RecipesApi.class);
        }
        return instance;
    }
}
