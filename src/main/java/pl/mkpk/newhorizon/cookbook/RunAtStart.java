package pl.mkpk.newhorizon.cookbook;

import org.springframework.stereotype.Component;
import pl.mkpk.newhorizon.repository.IngredientRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class RunAtStart {
    private IngredientRepository ingredientRepository;

    public RunAtStart(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @PostConstruct
    public void GenerateIngredients(){
        IngredientsBase ingredientsBase = new IngredientsBase();
        ingredientsBase.addIngredient(new Ingredient(
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

        List<Ingredient> ingredientList=ingredientRepository.findAll();

        for (Ingredient ingredient:ingredientList) {
            ingredientsBase.addIngredient(ingredient);
        }

        System.out.println(ingredientsBase);
    }
}
