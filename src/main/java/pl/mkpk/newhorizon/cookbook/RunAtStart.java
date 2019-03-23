package pl.mkpk.newhorizon.cookbook;

import org.springframework.stereotype.Component;
import pl.mkpk.newhorizon.repository.CookingRecipeRepository;
import pl.mkpk.newhorizon.repository.IngredientRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class RunAtStart {
    private IngredientRepository ingredientRepository;
    private CookingRecipeRepository cookingRecipeRepository;

    public RunAtStart(IngredientRepository ingredientRepository, CookingRecipeRepository cookingRecipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.cookingRecipeRepository = cookingRecipeRepository;
    }

    @PostConstruct
    public void GenerateIngredients(){
        //IngredientsBase ingredientsBase= new IngredientsBase();


        Ingredient newIngredient= new Ingredient(
                "szklanka mąki pszennej",
                new BigDecimal(250),
                StackType.GlASS,
                true,
                new BigDecimal(180));

        ingredientRepository.save(newIngredient);

        List<Ingredient> ingredientList=ingredientRepository.findAll();

        for (Ingredient ingredient:ingredientList) {
            System.out.println(ingredient);
        }

        CookingRecipe recipe= new CookingRecipe.RecipeBuilder(ingredientRepository)
                .setName("Jajko z jajkiem na jajku")
                .addIngredients( "Jajka",new BigDecimal(3))
                .addPreparationStep("Dodaj jajko do jajka i umieść na jaku.",new BigDecimal(60))
                .build();

        cookingRecipeRepository.save(recipe);

        System.out.println(recipe.toString());
    }
}
