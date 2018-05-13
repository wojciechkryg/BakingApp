package com.wojdor.bakingapp.application.recipes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

import static com.wojdor.bakingapp.application.utils.Constants.NO_ITEMS_COUNT;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private final OnItemClickListener onItemClickListener;
    private Context context;
    private List<Recipe> recipes;

    RecipesAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        setupRecipeIv(holder.imageIv, recipe);
        holder.nameTv.setText(recipe.getName());
    }

    private void setupRecipeIv(@NonNull ImageView imageIv, Recipe recipe) {
        if (isImageUrlInvalid(recipe)) return;
        Picasso.with(context)
                .load(recipe.getImageUrl())
                .error(R.drawable.ic_recipe_image)
                .into(imageIv);
    }

    private boolean isImageUrlInvalid(Recipe recipe) {
        return recipe.getImageUrl() == null || recipe.getImageUrl().trim().isEmpty();
    }

    @Override
    public int getItemCount() {
        return recipes == null ? NO_ITEMS_COUNT : recipes.size();
    }

    void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(Recipe recipe);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imageIv;
        final TextView nameTv;
        private RecipesAdapter.OnItemClickListener onItemClickListener;

        RecipeViewHolder(View view, RecipesAdapter.OnItemClickListener onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            nameTv = view.findViewById(R.id.item_recipe_name_tv);
            imageIv = view.findViewById(R.id.item_recipe_image_iv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Recipe recipe = recipes.get(clickedPosition);
            onItemClickListener.onItemClick(recipe);
        }
    }
}
