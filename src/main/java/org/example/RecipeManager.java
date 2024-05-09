package org.example;

import org.example.Enums.Category;
import org.example.Enums.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeManager {
    private List<Recipe> recipes;

    public RecipeManager() {
        this.recipes = new ArrayList<>();
    }

    public void createRecipe(Scanner scanner) {
        System.out.println("Enter recipe name:");
        String name = scanner.nextLine();

        System.out.println("Enter ingredients (enter 'done' when finished):");
        List<String> ingredients = new ArrayList<>();
        String ingredient;
        while (true) {
            System.out.print("Ingredient: ");
            ingredient = scanner.nextLine();
            if (ingredient.equalsIgnoreCase("done")) {
                break;
            }
            ingredients.add(ingredient);
        }

        System.out.println("Enter cooking instructions:");
        String cookingInstructions = scanner.nextLine();

        System.out.println("Enter serving size:");
        int servingSize = Integer.parseInt(scanner.nextLine());

        System.out.println("Choose three categories from the following list (enter 'done' after selecting each category):");
        Enums.Category[] categoryValues = Enums.Category.values();
        List<Enums.Category> categories = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("Available Categories:");
            for (int j = 0; j < categoryValues.length; j++) {
                System.out.println((j + 1) + ". " + categoryValues[j]);
            }
            System.out.print("Category " + (i + 1) + ": ");
            String categoryInput = scanner.nextLine();
            if (categoryInput.equalsIgnoreCase("done")) {
                break;
            }
            int categoryIndex = Integer.parseInt(categoryInput) - 1;
            if (categoryIndex < 0 || categoryIndex >= categoryValues.length) {
                System.out.println("Invalid category selection. Please try again.");
                i--; // Decrement i to allow user to re-enter category
                continue;
            }
            Enums.Category selectedCategory = categoryValues[categoryIndex];
            categories.add(selectedCategory);
        }

        System.out.println("Choose up to three tags from the following list (enter 'done' after selecting each tag):");
        Enums.Tag[] tagValues = Enums.Tag.values();
        List<Enums.Tag> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("Available Tags:");
            for (int j = 0; j < tagValues.length; j++) {
                System.out.println((j + 1) + ". " + tagValues[j]);
            }
            System.out.print("Tag " + (i + 1) + ": ");
            String tagInput = scanner.nextLine();
            if (tagInput.equalsIgnoreCase("done")) {
                break;
            }
            int tagIndex = Integer.parseInt(tagInput) - 1;
            if (tagIndex < 0 || tagIndex >= tagValues.length) {
                System.out.println("Invalid tag selection. Please try again.");
                i--; // Decrement i to allow user to re-enter tag
                continue;
            }
            Enums.Tag selectedTag = tagValues[tagIndex];
            tags.add(selectedTag);
        }

        Recipe recipe = new Recipe(name, ingredients, cookingInstructions, servingSize, categories, tags);
        recipes.add(recipe);
        System.out.println("Recipe saved successfully!");
    }

    // Other methods for modifying, searching, and rating recipes
    // ...
}

