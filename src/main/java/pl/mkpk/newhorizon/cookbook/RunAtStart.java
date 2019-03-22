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
                "szklanka mąki pszennej",
                new BigDecimal(250),
                StackType.GlASS,
                true,
                new BigDecimal(180))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "łyżeczka proszku do pieczenia",
                new BigDecimal(5),
                StackType.TEASPOON,
                false,
                new BigDecimal(65))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "szczypta soli",
                new BigDecimal(1),
                StackType.PINCH,
                false,
                new BigDecimal(18))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "łyżka cukru wanilinowego",
                new BigDecimal(15),
                StackType.SPOON,
                false,
                new BigDecimal(65))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "szklanka oleju roślinnego",
                new BigDecimal(250),
                StackType.GlASS,
                false,
                new BigDecimal(180))
        );
        ingredientsBase.addIngredient(new Ingredient(
                "szklanka mleka",
                new BigDecimal(250),
                StackType.GlASS,
                true,
                new BigDecimal(180))
        );
        Map<String, Ingredient> selects = ingredientsBase.ingredientMap;

        for(Map.Entry<String, Ingredient> entry : selects.entrySet()) {
            String key = entry.getKey();
            Ingredient value = entry.getValue();

            ingredientRepository.save(value);
        }*/

        /*{
            List<Ingredient> ingredient=ingredientRepository.findByName("Mąka");
            System.out.println(ingredient);
        }

        List<Ingredient> ingredientList=ingredientRepository.findAll();

        for (Ingredient ingredient:ingredientList) {
            ingredientsBase.addIngredient(ingredient);
        }

        System.out.println(ingredientsBase);*/

        /*CookingRecipe recipe= new CookingRecipe.RecipeBuilder(ingredientRepository)
                .setName("GOFRY DOMOWE - Lekkie i chrupiące")
                .addIngredients( "szklanka mąki pszennej",new BigDecimal(1.5))
                .addIngredients( "łyżeczka proszku do pieczenia",new BigDecimal(1.5))
                .addIngredients( "szczypta soli",new BigDecimal(1))
                .addIngredients( "łyżka cukru wanilinowego",new BigDecimal(1))
                .addIngredients( "Jajka",new BigDecimal(2))
                .addIngredients( "szklanka oleju roślinnego",new BigDecimal(0.5))
                .addIngredients( "szklanka mleka",new BigDecimal(1.33))
                .addPreparationStep("Mąkę wsypać do miski, dodać proszek do pieczenia, sól, cukier, cukier wanilinowy. Wszystko wymieszać a następnie dodać jajka, olej roślinny oraz mleko. ...",new BigDecimal(20))
                .build();

        cookingRecipeRepository.save(recipe);

        System.out.println(recipe.toString());*/
    }
}
