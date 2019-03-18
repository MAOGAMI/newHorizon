package pl.mkpk.newhorizon.cookbook;

import pl.mkpk.newhorizon.repository.IngredientRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CookingRecipe {

    @Id
    @GeneratedValue
    private int id;

    public String name;

    @ElementCollection
    @JoinTable(name = "ingredient_resource", joinColumns = @JoinColumn(name = "cr_id"))
    List<IngredientResource> ingredients;

    PreparationStep preparationSteps;

    public int numberOfPortions;
    public BigDecimal kalories;

    public List<IngredientResource> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientResource> ingredients) {
        this.ingredients = ingredients;
    }

    private CookingRecipe(RecipeBuilder builder) {
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.preparationSteps = builder.preparationSteps;
        this.numberOfPortions = 1;

        this.kalories = calculateCalories();
    }

    private BigDecimal calculateCalories() {
        BigDecimal result=new BigDecimal(0);

        for (IngredientResource ingrediendStack:ingredients) {
            result=result.add(ingrediendStack.calculateCalories());
        }
        return result;
    }

    public static class RecipeBuilder {
        private String name;
        private List<IngredientResource> ingredients;
        private PreparationStep preparationSteps;
        IngredientRepository base;

        //int numberOfPortions;

        boolean isName;
        boolean isIngredients;
        boolean isPreparationStep;

        public RecipeBuilder(IngredientRepository base){
            this.base=base;

            name="";
            ingredients=new ArrayList<>();
            //preparationSteps;
            isName=false;
            isIngredients=false;
            isPreparationStep=false;
        }

        public RecipeBuilder setName(String name){
            this.name=name;
            isName=true;
            return this;
        }

        public RecipeBuilder addIngredients(String ingredientName, BigDecimal numOf){

            this.ingredients.add(new IngredientResource(
                    //base.getIngredient(ingredientName),
                    base.findByName(ingredientName).get(0),
                    numOf));
            this.isIngredients=true;
            return this;
        }

        public RecipeBuilder addIngredients(Ingredient ingredient){

            this.ingredients.add(new IngredientResource(ingredient,new BigDecimal(5) ));
            this.isIngredients=true;
            return this;
        }

        public RecipeBuilder addPreparationStep(String descriptor,BigDecimal minutes){

            this.preparationSteps = new PreparationStep(descriptor,minutes);
            this.isPreparationStep=true;
            return this;
        }

        public CookingRecipe build(){
            if(isName==true && isIngredients==true && isPreparationStep==true){
                return new CookingRecipe(this);
            }else{
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "CookingRecipe{" +
                "name='" + this.name + "\'" +
                ", \n\tingredients=" + this.ingredients +
                ", \n\tpreparationSteps=" + this.preparationSteps +
                ", \n\tnumberOfPortions=" + this.numberOfPortions +
                ", \n\tkalories=" + this.kalories +
                '}';
    }
}
