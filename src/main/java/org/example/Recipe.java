package org.example;

import org.example.Enums.Category;
import org.example.Enums.Tag;
import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private String cookingInstructions;
    private int servingSize;
    private List<Category> categories;
    private List<Tag> tags;
    private double impact;

    public Recipe(String name, List<String> ingredients, String cookingInstructions, int servingSize, List<Category> categories, List<Tag> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;
        this.servingSize = servingSize;
        this.categories = categories;
        this.tags = tags;
        this.impact = 0.0; // Initialize impact property
    }

    // Getters and setters
    // ...

    // Method to compute impact based on chosen computation method
    public void computeImpact(String computationMethod) {
        // Compute impact based on computationMethod (average rating or total ratings)
        // ...
    }


}

