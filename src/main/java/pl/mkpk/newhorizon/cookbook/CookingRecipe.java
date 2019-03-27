package pl.mkpk.newhorizon.cookbook;

import pl.mkpk.newhorizon.repository.IngredientRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
//@Transactional()
public class CookingRecipe {

    @Id
    @GeneratedValue
    private int id;

    public String name;

    @ElementCollection // <--@Embedded - An ElementCollection mapping can be used to define a collection of Embeddable objects.
    //@Fetch(FetchMode.SUBSELECT)
    //@BatchSize(size=500)
    @JoinTable(name = "ingredient_resource", joinColumns = @JoinColumn(name = "cr_id"))
    /*@CollectionTable(name = "ingredient_resource",
            joinColumns = @JoinColumn(name="cr_id"))*/
    List<IngredientResource> ingredients;// = new ArrayList<>();

    PreparationStep preparationSteps;

    public int numberOfPortions;
    public BigDecimal calories;

    public List<IngredientResource> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientResource> ingredients) {
        this.ingredients = ingredients;
    }

    private CookingRecipe() {}

    private CookingRecipe(RecipeBuilder builder) {
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.preparationSteps = builder.preparationSteps;
        this.numberOfPortions = 1;

        this.calories = calculateCalories();
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

        //konwencja: string builder -> number(BigDecimal) of(StackType) ingredient(Ingredient.name)
        public RecipeBuilder addIngredients(String ingredientData){

            List<String> stringList = Arrays.asList(ingredientData.split(" "));
            BigDecimal number = BigDecimal.valueOf(Double.parseDouble(stringList.get(0)));

            StackType ofStack = StackType.fromString(stringList.get(1));

            //String ingredientName = String.join(" ", stringList);
            String ingredientName="";
            for (int i=2;i<stringList.size();i++){
                ingredientName+=stringList.get(i);
                if(i!=stringList.size()-1){
                    ingredientName+=" ";
                }
            }

            System.out.println("Search ingredient named: "+ ingredientName);

            List<Ingredient> findIngredients = base.findByName(ingredientName);
            Ingredient correctIngredient=null;

            System.out.println("Number finded: "+ findIngredients.size());

            for (Ingredient currenIngredient: findIngredients){
                if(currenIngredient.unit.equals(ofStack)){
                    correctIngredient=currenIngredient;
                    break;
                }
            }
            if(correctIngredient==null){
                System.out.println("Nie znaleziono składnika dla podanej miary objętości i wagi.");
                //throw new RuntimeException("Nie znaleziono składnika dla podanej miary objętości i wagi.!!!!!!!!!!!!!");
                if(findIngredients.size()>0){
                    System.out.println("Tworzenie składnika z innej miary.");
                    correctIngredient=new Ingredient(ingredientName,  new BigDecimal(100), ofStack, findIngredients.get(0).alergen, findIngredients.get(0).calories);
                    base.save(correctIngredient);
                }

            }

            this.ingredients.add(new IngredientResource(
                    correctIngredient,
                    number));
            this.isIngredients=true;
            return this;
        }


        /*public RecipeBuilder addIngredients(String ingredientName, BigDecimal numOf){

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
        }*/

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
                ", \n\tcalories=" + this.calories +
                '}';
    }
}
