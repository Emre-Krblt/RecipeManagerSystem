package org.example;
import RateModule.RatingRecipe;
import RateModule.RatingRecipeDisplay;
import SearchModule.SearchingByCategories;
import SearchModule.SearchingByIngredients;
import SearchModule.SearchingByTags;
import SearchModule.SearchingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class RecipeManager {
    private static final List<Recipe> recipes = new ArrayList<>();
    private static final List<String> predefinedCategories = List.of("Dessert", "Main Dishes", "Beverage","Breakfast","Dinner","Lunch","Salad","Snack","Soup");
    private static final List<String> predefinedTags = List.of("Hot", "Easy", "Quick", "Vegetarian", "Healthy", "Vegan", "Low Carb", "Low Fat", "Low Calorie","Spicy","Sweet");

    public static void mainMenu() {
        RecipeManager recipeManager = new RecipeManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Create Recipe");
            System.out.println("2. Search Recipes");
            System.out.println("3. Rate Recipe");
            System.out.println("4. Modify Recipe");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = readIntInput(scanner);
            switch (option) {
                case 1 -> recipeManager.createRecipe();
                case 2 -> recipeManager.searchRecipe();
                case 3 -> recipeManager.rateRecipe();
                case 4 -> recipeManager.modifyRecipe();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Recipe menemen = new Recipe("Soğanlı Menemen", List.of( "Pepper", "Eggs", "Tomato", "Onion"), "Fry the onion,Fry the tomatoes,Whisk eggs,Mix everything and cook", "S", List.of("Main Dishes"), List.of("Hot"), 4);
        Recipe cagKebab = new Recipe("Cağ Kebab", List.of("Lamb meat", "Thyme", "Black Pepper", "Tail fat", "Pide", "Garlic"), "\n" + "Arrange the meat on the skewer, then gently rotate it around the wood fire, cut off the cooked parts and place them on the plate.", "M", List.of("Main Dishes"), List.of("Healthy", "Quick and Easy"), 5);
        Recipe lemonade = new Recipe("Lemonade", List.of("Frozen Lemonade", "Sugar", "Water", "Ice", "Lemon"), "Add all ingredients to a blender,Blend until smooth,Pour into glasses and serve", "M", List.of("Beverages", "Breakfast"), List.of("Vegetarian", "Healthy"), 3);
        recipes.add(menemen);
        recipes.add(cagKebab);
        recipes.add(lemonade);
        mainMenu();
    }

    private static int readIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void createRecipe() {
        Scanner scanner = new Scanner(System.in);
        String title = "";
        List<String> ingredients = new ArrayList<>();
        String cookingInstructions = "";
        String servingSize = "";
        List<String> categories = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        while (true) {
            System.out.println("Creating Recipe");
            System.out.println("1. Enter title: " + title);
            System.out.println("2. Enter ingredients: " + ingredients);
            System.out.println("3. Enter cooking instructions: " + cookingInstructions);
            System.out.println("4. Enter serving size(S, M or L): " + servingSize);
            System.out.println("5. Enter up to three categories: " + categories);
            System.out.println("6. Enter up to three tags: " + tags);
            System.out.println("7. Save recipe");
            System.out.println("8. Back");
            System.out.print("Select an option: ");
            int option = readIntInput(scanner);
            scanner.nextLine();

            switch (option) {
                case 1:
                    while (true) {
                        System.out.print("Enter title: ");
                        title = scanner.nextLine();
                        if (!title.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Invalid title. Please enter a string.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter ingredients(Type \"done\" to finish the process): ");
                    String ingredient;
                    do {
                        ingredient = scanner.nextLine();
                        if (!ingredient.isEmpty() && !ingredient.equalsIgnoreCase("done") && !ingredient.matches("\\d+")) {
                            ingredients.add(ingredient);
                        }else{
                            System.out.println("Invalid ingredient. Please enter a string.");
                        }
                    } while (!ingredient.equalsIgnoreCase("done"));
                    break;
                case 3:
                    System.out.print("Enter cooking instructions: ");
                    cookingInstructions = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter serving size(S, M or L): ");
                    while (true) {
                        servingSize = scanner.nextLine();
                        if (servingSize.equals("S") || servingSize.equals("M") || servingSize.equals("L")) {
                            break;
                        } else {
                            System.out.print("Try again, Enter serving size(S, M or L): ");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter up to three categories (press Enter after each category, or enter 'done' to finish):");
                    System.out.println("Available categories: " + predefinedCategories);
                    String category;
                    do {
                        category = scanner.nextLine();
                        if (!category.isEmpty() && !category.equalsIgnoreCase("done") && predefinedCategories.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(category.toLowerCase())) {
                            categories.add(category);
                        }else{
                            System.out.println("Invalid category. Please enter a category from the available categories.");
                        }
                    } while (!category.equalsIgnoreCase("done") && categories.size() < 3);
                    break;
                case 6:
                    System.out.println("Enter up to three tags (press Enter after each tag, or enter 'done' to finish):");
                    System.out.println("Available tags: " + predefinedTags);
                    String tag;
                    do {
                        tag = scanner.nextLine();
                        if (!tag.isEmpty() && !tag.equalsIgnoreCase("done") && predefinedTags.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(tag.toLowerCase())) {
                            tags.add(tag);
                        }else {
                            System.out.println("Invalid tag. Please enter a tag from the available tags.");
                        }
                    } while (!tag.equalsIgnoreCase("done") && tags.size() < 3);
                    break;
                case 7:
                    if (!title.equals("") && !ingredients.isEmpty() && !cookingInstructions.equals("") && !servingSize.equals("") && !categories.isEmpty() && !tags.isEmpty()) {
                        Recipe recipe = FactoryRecipe.createRecipe(title, ingredients, cookingInstructions, servingSize, categories, tags);
                        recipes.add(recipe);
                        System.out.println("Recipe saved successfully!");
                    } else System.out.println("Please enter recipe details before saving.");
                    break;
                case 8:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void searchRecipe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select that you want to search based on what?");
        System.out.println("1. Search based on ingredients");
        System.out.println("2. Search based on categories");
        System.out.println("3. Search based on tags");
        System.out.println("4. Back to main menu");
        String choice = scanner.nextLine();
        int option;
        try {
            option = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid option. Please enter a number.");
            searchRecipe();
            return;
        }

        while (true) {
            switch (option) {
                case 1:{
                    System.out.print("Enter keyword: ");
                    SearchingRecipe searchingRecipe = new SearchingRecipe();
                    searchingRecipe.setSearchStrategy(new SearchingByIngredients());
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = searchingRecipe.searchRecipes(recipes, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No results");
                        searchRecipe();
                    } else {
                        for (int i = 0; i < searchResults.size(); i++) {
                            System.out.println((i + 1) + "-" + searchResults.get(i).getTitle());
                        }
                        viewDetails(searchResults);
                    }
                    break;
                }
                case 2:{
                    System.out.print("Enter keyword: ");
                    SearchingRecipe searchingRecipe = new SearchingRecipe();
                    searchingRecipe.setSearchStrategy(new SearchingByCategories());
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = searchingRecipe.searchRecipes(recipes, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No results");
                        searchRecipe();
                    } else {
                        for (int i = 0; i < searchResults.size(); i++) {
                            System.out.println((i + 1) + "-" + searchResults.get(i).getTitle());
                        }
                        viewDetails(searchResults);
                    }
                    break;
                }
                case 3:{
                    System.out.print("Enter keyword: ");
                    SearchingRecipe searchingRecipe = new SearchingRecipe();
                    searchingRecipe.setSearchStrategy(new SearchingByTags());
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = searchingRecipe.searchRecipes(recipes, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No results");
                        searchRecipe();
                    } else {
                        for (int i = 0; i < searchResults.size(); i++) {
                            System.out.println((i + 1) + "-" + searchResults.get(i).getTitle());
                        }
                        viewDetails(searchResults);
                    }
                    break;
                }
                case 4:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    return;
            }
        }
    }

    public void rateRecipe() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + "-" + recipes.get(i).getTitle() + "- Rating: " + recipes.get(i).getRating());
        }
        System.out.println("Select the recipe to rate(0 to main menu):");
        String choice = scanner.nextLine();
        int option;
        try {
            option = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid option. Please enter a number.");
            rateRecipe();
            return;
        }
        if (option == 0) {
            mainMenu();
        } else if (option < 0 || option > recipes.size()) {
            System.out.println("Invalid option. Please enter a number between 0 and " + recipes.size());
            rateRecipe();
            return;
        }
        Recipe selectedRecipe = recipes.get(option - 1);
        RatingRecipe ratingRecipe = new RatingRecipe();
        RatingRecipeDisplay ratingRecipeDisplay = new RatingRecipeDisplay(ratingRecipe);
        int rating;
        while (true) {
            System.out.println("Enter recipe rating (1-5): ");
            try {
                rating = Integer.parseInt(scanner.nextLine());
                if (rating >= 1 && rating <= 5) {
                    break;
                } else {
                    System.out.println("Invalid rating. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating. Please enter a number between 1 and 5.");
            }
        }
        ratingRecipe.setRating(selectedRecipe, rating);
        rateRecipe();
    }

    public void viewDetails(List<Recipe> searchResults) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the details you want to see:");
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);
        Recipe selected = searchResults.get(option - 1);
        System.out.println("Title: " + selected.getTitle());
        System.out.println("Ingredients: " + selected.getIngredients());
        System.out.println("Instructions: " + selected.getInstructions());
        System.out.println("Serving Size: " + selected.getServingSize());
        System.out.println("Categories: " + selected.getCategories());
        System.out.println("Tags: " + selected.getTags());
        System.out.println("Enter 1 to go main menu");
        while (true) {
            int back = readIntInput(scanner);
            if (back == 1) {
                mainMenu();
            }
        }
    }

    public void modifyRecipe() {
        ModifyRecipeInvoker invoker = new ModifyRecipeInvoker();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + "-" + recipes.get(i).getTitle() + "\nIngredients: " + recipes.get(i).getIngredients() + "\nInstructions: " + recipes.get(i).getInstructions() + "\nServing Size: " + recipes.get(i).getServingSize() + "\nCategories: " + recipes.get(i).getCategories() + "\nTags: " + recipes.get(i).getTags() + "\n---------------------");
        }
        System.out.println("Please enter the number of recipe: ");
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);

        String title = recipes.get(option - 1).getTitle();
        List<String> ingredients = new ArrayList<>(recipes.get(option - 1).getIngredients()); // Create a new list
        String cookingInstructions = recipes.get(option - 1).getInstructions();
        String servingSize = recipes.get(option - 1).getServingSize();
        List<String> categories = new ArrayList<>(recipes.get(option - 1).getCategories()); // Create a new list
        List<String> tags = new ArrayList<>(recipes.get(option - 1).getTags()); // Create a new list
        while (true) {
            System.out.println("Modifying Recipe");
            System.out.println("1. Enter new title: " + title);
            System.out.println("2. Enter new ingredients: " + ingredients);
            System.out.println("3. Enter new cooking instructions: " + cookingInstructions);
            System.out.println("4. Enter new serving size(S, M or L): " + servingSize);
            System.out.println("5. Enter new up to three categories: " + categories);
            System.out.println("6. Enter new up to three tags: " + tags);
            System.out.println("7. Save recipe");
            System.out.println("8. Undo last modifications(If you didn't save, can't undo)");
            System.out.println("9. Back to main menu");
            System.out.print("Select an option: ");
            int option1 = readIntInput(scanner);
            scanner.nextLine();


            switch (option1) {
                case 1:
                    while (true) {
                        System.out.print("Enter title: ");
                        title = scanner.nextLine();
                        if (!title.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Invalid title. Please enter a string.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter ingredients: ");
                    String ingredient;
                    do {
                        ingredient = scanner.nextLine();
                        if (!ingredient.isEmpty() && !ingredient.equalsIgnoreCase("done") && !ingredient.matches("\\d+")) {
                            ingredients.add(ingredient);
                        } else {
                            System.out.println("Invalid ingredient. Please enter a string.");
                        }
                    } while (!ingredient.equalsIgnoreCase("done"));
                    break;
                case 3:
                    System.out.print("Enter cooking instructions: ");
                    cookingInstructions = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter serving size(S, M or L): ");
                    while (true) {
                        servingSize = scanner.nextLine();
                        if (servingSize.equals("S") || servingSize.equals("M") || servingSize.equals("L")) {
                            break;
                        } else {
                            System.out.print("Try again, Enter serving size(S, M or L): ");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter up to three categories (press Enter after each category, or enter 'done' to finish):");
                    System.out.println("Available categories: " + predefinedCategories);
                    String category;
                    do {
                        category = scanner.nextLine();
                        if (!category.isEmpty() && !category.equalsIgnoreCase("done") && predefinedCategories.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(category.toLowerCase())) {
                            categories.add(category);
                        } else {
                            System.out.println("Invalid category. Please enter a category from the available categories.");
                        }
                    } while (!category.equalsIgnoreCase("done") && categories.size() < 3);
                    break;
                case 6:
                    System.out.println("Enter up to three tags (press Enter after each tag, or enter 'done' to finish):");
                    System.out.println("Available tags: " + predefinedTags);
                    String tag;
                    do {
                        tag = scanner.nextLine();
                        if (!tag.isEmpty() && !tag.equalsIgnoreCase("done") && predefinedTags.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(tag.toLowerCase())) {
                            tags.add(tag);
                        } else {
                            System.out.println("Invalid tag. Please enter a tag from the available tags.");
                        }
                    } while (!tag.equalsIgnoreCase("done") && tags.size() < 3);
                    break;
                case 7:
                    Command modifyCommand = new ModifyRecipe(recipes.get(option - 1), title, servingSize, ingredients, cookingInstructions, categories, tags);
                    invoker.executeCommand(modifyCommand);
                    break;
                case 8:
                    invoker.undoLastCommand();
                    modifyRecipe();
                    break;
                case 9:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
