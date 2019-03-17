package pl.mkpk.newhorizon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkpk.newhorizon.model.Recipe;
import pl.mkpk.newhorizon.repository.RecipeRepository;

@Service("recipeService")
public class RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public boolean addRecipe(Recipe recipe){
        try {
            recipeRepository.save(recipe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Recipe findByRecipeName(String recipeName){
        return recipeRepository.findByRecipeName(recipeName);
    }
}
