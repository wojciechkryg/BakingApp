package com.wojdor.bakingapp.application.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private final OnItemClickListener onItemClickListener;
    private List<Recipe> recipes;

    RecipesAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.nameTv.setText(recipe.getName());
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

        final TextView nameTv;
        private RecipesAdapter.OnItemClickListener onItemClickListener;

        RecipeViewHolder(View view, RecipesAdapter.OnItemClickListener onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            nameTv = view.findViewById(R.id.item_recipe_name_tv);
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
