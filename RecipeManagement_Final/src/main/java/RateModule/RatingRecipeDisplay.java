package RateModule;

public class RatingRecipeDisplay implements RatingObserver {
    private RatingRecipe ratingRecipe;

    public RatingRecipeDisplay(RatingRecipe ratingRecipe) {
        this.ratingRecipe = ratingRecipe;
        ratingRecipe.addObserver(this);
    }

    @Override
    public void update(float rating) {
        System.out.println("Recipe rating updated: " + rating);
    }
}
