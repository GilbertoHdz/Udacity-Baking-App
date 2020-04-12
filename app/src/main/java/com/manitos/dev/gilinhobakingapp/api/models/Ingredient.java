package com.manitos.dev.gilinhobakingapp.api.models;

import java.io.Serializable;

/**
 * Created by gilberto hdz on 11/04/20.
 */
public class Ingredient implements Serializable {

    private Double quantity;
    private String measure;
    private String ingredient;

    public Ingredient(Double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
