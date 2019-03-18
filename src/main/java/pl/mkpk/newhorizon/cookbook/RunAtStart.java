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
        IngredientsBase ingredientsBase= new IngredientsBase();
        /*ingredientsBase.addIngredient(new Ingredient(
                "Jajka",
                new BigDecimal(65),
                StackType.UNIT,
                true,
                new BigDecimal(180))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "Mleko",
                new BigDecimal(100),
                StackType.ML,
                true,
                new BigDecimal(65))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "Mąka",
                new BigDecimal(100),
                StackType.GRAMS,
                true,
                new BigDecimal(18))
        );
        Map<String, Ingredient> selects = ingredientsBase.ingredientMap;

        for(Map.Entry<String, Ingredient> entry : selects.entrySet()) {
            String key = entry.getKey();
            Ingredient value = entry.getValue();

            ingredientRepository.save(value);
        }

        {
            List<Ingredient> ingredient=ingredientRepository.findByName("Mąka");
            System.out.println(ingredient);
        }

        List<Ingredient> ingredientList=ingredientRepository.findAll();

        for (Ingredient ingredient:ingredientList) {
            ingredientsBase.addIngredient(ingredient);
        }

        System.out.println(ingredientsBase);*/

        CookingRecipe recipe= new CookingRecipe.RecipeBuilder(ingredientRepository)
                .setName("test next recipe")
                .addIngredients( "Jajka",new BigDecimal(12))
                .addIngredients( "Mleko",new BigDecimal(300))
                .addIngredients( "Mąka",new BigDecimal(500))
                .addPreparationStep("Wbij więcej jaj do mąki. Dolej mleka. Wymieszaj. Wtaw do piekarnika nagrzanego do 100* na 30 minut.",new BigDecimal(6))
                .build();

        cookingRecipeRepository.save(recipe);

        System.out.println(recipe.toString());
    }
}
