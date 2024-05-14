package SearchModule;

import org.example.Recipe;

import java.util.List;

public interface SearchStrategy {
    List<Recipe> search(List<Recipe> recipes, String keyword);
}
