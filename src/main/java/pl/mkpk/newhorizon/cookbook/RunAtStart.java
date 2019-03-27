package pl.mkpk.newhorizon.cookbook;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import pl.mkpk.newhorizon.repository.CookingRecipeRepository;
import pl.mkpk.newhorizon.repository.IngredientRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
//@Transactional
public class RunAtStart {
    private IngredientRepository ingredientRepository;
    private CookingRecipeRepository cookingRecipeRepository;

    public RunAtStart(IngredientRepository ingredientRepository, CookingRecipeRepository cookingRecipeRepository,
                      PlatformTransactionManager transactionManager) {
        this.ingredientRepository = ingredientRepository;
        this.cookingRecipeRepository = cookingRecipeRepository;

        Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
        this.transactionTemplate=new TransactionTemplate(transactionManager);
    }

    private final TransactionTemplate transactionTemplate;

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

        /*CookingRecipe recipe= new CookingRecipe.RecipeBuilder(ingredientRepository)
                .setName("Jajko z niespodzianką 3")
                .addIngredients( "1 sztuka Jajka")
                .addIngredients( "12 szklanki mąki pszennej")
                .addPreparationStep("Poprostu coś 6.",new BigDecimal(1))
                .build();

        cookingRecipeRepository.save(recipe);*/

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                List<CookingRecipe> recipes=cookingRecipeRepository.findAll();
                System.out.println();
                System.out.println(recipes);
                System.out.println();
                //System.out.println(recipes.get(0).getIngredients());
                //System.out.println();
            }
        });

        /*CookingRecipe recipe=cookingRecipeRepository.findAll().get(0);
        System.out.println(recipe);*/
    }
}
