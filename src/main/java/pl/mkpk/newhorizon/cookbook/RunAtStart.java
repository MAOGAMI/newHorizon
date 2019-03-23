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

        /*Ingredient newIngredient= new Ingredient(
                "szklanka mąki pszennej",
                new BigDecimal(250),
                StackType.GLASS,
                true,
                new BigDecimal(180));

        ingredientRepository.save(newIngredient);*/

        /*List<Ingredient> ingredientList=ingredientRepository.findAll();

        for (Ingredient ingredient:ingredientList) {
            System.out.println(ingredient);
        }*/

        CookingRecipe recipe= new CookingRecipe.RecipeBuilder(ingredientRepository)
                .setName("Jajko ze wszystkim(wersja 3)")
                .addIngredients( "3 sztuki Jajka")
                .addIngredients( "3 łyżka cukru wanilinowego")
                .addIngredients( "3.50 szklanki oleju roślinnego")
                .addIngredients( "3 szklanka mąki pszennej")
                .addIngredients( "0.50 szklanki mleka")
                .addIngredients( "1 szczypta soli")
                .addIngredients( "3 łyżeczki proszku do pieczenia")
                .addPreparationStep("Dodaj jajko do reszty i umieść w misce.",new BigDecimal(60))
                .build();

        cookingRecipeRepository.save(recipe);

        System.out.println(recipe.toString());
    }
}
