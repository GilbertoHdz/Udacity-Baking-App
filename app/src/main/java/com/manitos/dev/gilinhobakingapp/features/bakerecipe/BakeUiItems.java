package com.manitos.dev.gilinhobakingapp.features.bakerecipe;

import com.manitos.dev.gilinhobakingapp.api.models.Ingredient;
import com.manitos.dev.gilinhobakingapp.api.models.Step;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gilberto hdz on 11/04/20.
 */
public class BakeUiItems implements Serializable {
    private String stepType;
    private List<Ingredient> ingredients;
    private Step step;

    public BakeUiItems(String stepType, List<Ingredient> ingredients, Step step) {
        this.stepType = stepType;
        this.ingredients = ingredients;
        this.step = step;
    }

    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
