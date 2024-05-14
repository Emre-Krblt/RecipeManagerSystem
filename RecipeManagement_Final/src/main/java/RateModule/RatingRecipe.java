package RateModule;
import org.example.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RatingRecipe {
    private List<RatingObserver> observers;
    private float rating;

    public RatingRecipe() {
        observers = new ArrayList<>();
        rating = 0;
    }

    public void addObserver(RatingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RatingObserver observer) {
        observers.remove(observer);
    }

    public void setRating(Recipe recipe, float rating) {
        float oldRating = recipe.getRating();
        float ratedCount = recipe.getRated();
        float newRating = ((oldRating * ratedCount) + rating) / (ratedCount + 1);
        recipe.setRating(newRating);
        recipe.incrementRated();
        this.rating = newRating;
        notifyObservers();
    }

    private void notifyObservers() {
        for (RatingObserver observer : observers) {
            observer.update(rating);
        }
    }
}
