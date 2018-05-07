package com.wojdor.bakingapp.data.utils;

import com.wojdor.bakingapp.data.model.StepModel;
import com.wojdor.bakingapp.domain.Step;

import java.util.ArrayList;
import java.util.List;

public final class StepMapper {

    private static StepMapper instance;

    private StepMapper() {
    }

    public static StepMapper getInstance() {
        if (instance == null) {
            instance = new StepMapper();
        }
        return instance;
    }

    private Step map(StepModel model) {
        return new Step(
                model.getId(),
                model.getShortDescription(),
                model.getDescription(),
                model.getVideoURL(),
                model.getThumbnailURL()
        );
    }

    public List<Step> map(List<StepModel> models) {
        List<Step> steps = new ArrayList<>();
        for (StepModel model : models) {
            steps.add(map(model));
        }
        return steps;
    }
}