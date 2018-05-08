package com.wojdor.bakingapp.application.recipedetails.steps;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.domain.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private final OnItemClickListener onItemClickListener;
    private List<Step> steps;

    StepsAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_step, parent, false);
        return new StepsViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.shortDescriptionTv.setText(step.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps == null ? NO_ITEMS_COUNT : steps.size();
    }

    void setSteps(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(Step step);
    }

    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView shortDescriptionTv;
        private StepsAdapter.OnItemClickListener onItemClickListener;

        StepsViewHolder(View view, StepsAdapter.OnItemClickListener onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            shortDescriptionTv = view.findViewById(R.id.item_step_short_description_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Step step = steps.get(clickedPosition);
            onItemClickListener.onItemClick(step);
        }
    }
}
