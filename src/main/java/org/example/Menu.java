package org.example;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeManager recipeManager = new RecipeManager();

        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Create Recipe");
            System.out.println("2. Search Recipes");
            System.out.println("3. Rate Recipe");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    recipeManager.createRecipe(scanner);
                    break;
                case 2:
                    // Implement search recipe functionality
                    break;
                case 3:
                    // Implement rate recipe functionality
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        scanner.close();
    }
}
